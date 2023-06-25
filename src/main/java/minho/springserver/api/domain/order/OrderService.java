package minho.springserver.api.domain.order;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.ItemInfo;
import minho.springserver.api.domain.item.entity.Items;
import minho.springserver.api.domain.order.entity.OrderItemOptionGroups;
import minho.springserver.api.domain.order.entity.OrderItemOptions;
import minho.springserver.api.domain.order.entity.OrderItems;
import minho.springserver.api.domain.order.entity.Orders;
import minho.springserver.api.domain.order.input.OrderCommand;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderCreate orderCreate;
    private final OrderRead orderRead;

    @Transactional
    public Long createdOrder(OrderCommand.CreateOrderCommand command) {
        // order entity 초기화 (command -> entity) 및 order 저장
        Orders initOrder = Orders.init(command);
        Long insertedOrderId = this.orderCreate.insertOrder(initOrder);

        // order item entity, order item option group entity, order item option entity 초기화
        List<OrderItems> initOrderItems = new ArrayList<>();
        List<OrderItemOptions> initOrderItemOptions = new ArrayList<>();
        List<OrderItemOptionGroups> initOrderItemOptionGroups = new ArrayList<>();

        List<Long> orderItemIds = command.getOrderItems().stream()
                .map(orderItem -> orderItem.getOrderItemId())
                .collect(Collectors.toList());
        List<Optional<Items>> items = orderItemIds.stream()
                .map(orderItemId -> {
                    // 해당 item database 조회
                    Optional<Items> item = this.orderRead.findItem(orderItemId);
                    List<ItemInfo.ItemOptionGroup> itemOptionGroups = this.orderRead.findItemOptionGroups(item.get());

                    // order item entity init 생성
                    OrderItems initOrderItem = OrderItems.init(initOrder, 3, item.get());
                    initOrderItems.add(initOrderItem);

                    // order item option group entity init 생성
                    itemOptionGroups.forEach(itemOptionGroup -> {
                        OrderItemOptionGroups initItemOptionGroup = OrderItemOptionGroups.init(initOrderItem, itemOptionGroup);
                        initOrderItemOptionGroups.add(initItemOptionGroup);

                        // order item option entity init 생성
                        itemOptionGroup.getItemOptions().forEach(itemOption -> {
                            OrderItemOptions initItemOption = OrderItemOptions.init(initItemOptionGroup, itemOption);
                        });
                    });

                    return item;
                })
                .collect(Collectors.toList());

        // order item entity, order item option group entity, order item option entity 저장
        this.orderCreate.insertOrderItems(initOrderItems);
        this.orderCreate.insertOrderItemOptionGroups(initOrderItemOptionGroups);
        this.orderCreate.insertOrderItemOptions(initOrderItemOptions);

        return insertedOrderId;
    }

}

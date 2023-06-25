package minho.springserver.api.domain.order;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.ItemInfo;
import minho.springserver.api.domain.item.entity.Items;
import minho.springserver.api.domain.order.entity.OrderItemOptionGroups;
import minho.springserver.api.domain.order.entity.OrderItemOptions;
import minho.springserver.api.domain.order.entity.OrderItems;
import minho.springserver.api.domain.order.entity.Orders;
import minho.springserver.api.domain.order.input.OrderCommand;
import minho.springserver.exception.BadRequestException;
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
        // order entity 초기화 (command -> entity) 및 order 저장 (aggregate table)
        Orders initOrder = Orders.init(command);
        Long insertedOrderId = this.orderCreate.insertOrder(initOrder);

        // order item entity, order item option group entity, order item option entity 초기화 (children table)
        List<OrderItems> initOrderItems = new ArrayList<>();
        List<OrderItemOptions> initOrderItemOptions = new ArrayList<>();
        List<OrderItemOptionGroups> initOrderItemOptionGroups = new ArrayList<>();

        // child table
        command.getOrderItems().forEach(orderItem -> {
            // 1. order item entity init 생성
            Long orderItemId = orderItem.getOrderItemId();
            Optional<Items> item = this.orderRead.findItem(orderItemId);
            // if (item.isEmpty()) throw new BadRequestException("item does not exits");
            OrderItems initOrderItem = OrderItems.init(initOrder, 3, item.get());
            initOrderItems.add(initOrderItem);

            orderItem.getOrderItemOptionGroups().forEach(orderItemOptionGroup -> {
                // 2. order item option group entity init 생성
                Long orderItemOptionGroupId = orderItemOptionGroup.getOrderItemOptionGroupId();
                item.get().getItemOptionGroups()
                        .stream()
                        .filter(itemOptionGroup -> itemOptionGroup.getId() == orderItemOptionGroupId)
                        .forEach(itemOptionGroup -> {
                            OrderItemOptionGroups initOrderItemOptionGroup = OrderItemOptionGroups.init(initOrderItem, itemOptionGroup);
                            initOrderItemOptionGroups.add(initOrderItemOptionGroup);
                        });

                // 3. order item option entity init 생성
//                orderItemOptionGroup.getOrderItemOptions().forEach(orderItemOption -> {
//                    Long orderItemOptionId = orderItemOption.getOrderItemOptionId();
//                    item.get().getItemOptionGroups().forEach(itemOptionGroup -> {
//                        i
//                    });
//                });

//                // if (item.isEmpty()) throw new BadRequestException("item does not exits");
//                OrderItems initOrderItem = OrderItems.init(initOrder, 3, item.get());
//                initOrderItems.add(initOrderItem);
            });

        });

        // order item entity, order item option group entity, order item option entity 저장
        this.orderCreate.insertOrderItems(initOrderItems);
        this.orderCreate.insertOrderItemOptionGroups(initOrderItemOptionGroups);
        this.orderCreate.insertOrderItemOptions(initOrderItemOptions);

        return insertedOrderId;
    }

}

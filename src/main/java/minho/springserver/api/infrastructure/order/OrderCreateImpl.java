package minho.springserver.api.infrastructure.order;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.order.OrderCreate;
import minho.springserver.api.domain.order.entity.OrderItemOptionGroups;
import minho.springserver.api.domain.order.entity.OrderItemOptions;
import minho.springserver.api.domain.order.entity.OrderItems;
import minho.springserver.api.domain.order.entity.Orders;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderCreateImpl implements OrderCreate {
    private final OrdersRepository ordersRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final OrderItemOptionGroupsRepository orderItemOptionGroupsRepository;
    private final OrderItemOptionsRepository orderItemOptionsRepository;

    @Override
    public Long insertOrder(Orders order) {
        Orders savedItem = this.ordersRepository.save(order);
        Long savedId = savedItem.getId();
        return savedId;
    }


    @Override
    public List<Long> insertOrderItems(List<OrderItems> orderItems) {
        List<Long> savedIds = orderItems.stream()
                .map(orderItem -> {
                    OrderItems savedOrderItem = this.orderItemsRepository.save(orderItem);
                    Long savedId = savedOrderItem.getId();
                    return savedId;
                })
                .collect(Collectors.toList());

        return savedIds;
    }

    @Override
    public List<Long> insertOrderItemOptionGroups(List<OrderItemOptionGroups> orderItemOptionGroups) {
        List<Long> savedIds = orderItemOptionGroups.stream()
                .map(orderItemOptionGroup -> {
                    OrderItemOptionGroups savedOrderOptionGroup = this.orderItemOptionGroupsRepository.save(orderItemOptionGroup);
                    Long savedId = savedOrderOptionGroup.getId();
                    return savedId;
                })
                .collect(Collectors.toList());

        return savedIds;
    }

    @Override
    public List<Long> insertOrderItemOptions(List<OrderItemOptions> orderItemOptions) {
        List<Long> savedIds = orderItemOptions.stream()
                .map(orderItemOption -> {
                    OrderItemOptions savedOrderItemOption = this.orderItemOptionsRepository.save(orderItemOption);
                    Long savedId = savedOrderItemOption.getId();
                    return savedId;
                })
                .collect(Collectors.toList());

        return savedIds;
    }
}

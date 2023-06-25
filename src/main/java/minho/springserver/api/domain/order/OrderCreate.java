package minho.springserver.api.domain.order;

import minho.springserver.api.domain.order.entity.OrderItemOptionGroups;
import minho.springserver.api.domain.order.entity.OrderItemOptions;
import minho.springserver.api.domain.order.entity.OrderItems;
import minho.springserver.api.domain.order.entity.Orders;

import java.util.List;

public interface OrderCreate {
    Long insertOrder(Orders order);

    List<Long> insertOrderItems(List<OrderItems> orderItems);

    List<Long> insertOrderItemOptionGroups(List<OrderItemOptionGroups> orderItemOptionGroups);

    List<Long> insertOrderItemOptions(List<OrderItemOptions> orderItemOptions);
}

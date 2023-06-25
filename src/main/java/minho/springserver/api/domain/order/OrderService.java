package minho.springserver.api.domain.order;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.order.entity.OrderItemOptionGroups;
import minho.springserver.api.domain.order.entity.OrderItems;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    @Transactional
    public Long createdOrder(OrderCommand.CreateOrderCommand command) {
        // order entity 초기화 (command -> entity) 및 order 저장
        Orders initOrder = Orders.init(command);
        Long insertedOrderId = this.orderCreate.insertOrder(initOrder);

        // order item entity, order item option group entity, order item option entity 초기화
        Long itemId = command.getItemId();
        OrderItems initOrderItem = OrderItems.init(order, command);

//        List<OrderItemOptionGroups> initOrderItemOptionGroups = command.getItemstream().


    }

}

package minho.springserver.api.application.order;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.order.OrderService;
import minho.springserver.api.domain.order.input.OrderCommand;
import org.springframework.stereotype.Component;

// Application -> Create/Read/Modify/Remove로 정의
@Component
@RequiredArgsConstructor
public class OrderApplication {
    private final OrderService orderService;

    public Long createOrder(OrderCommand.CreateOrderCommand command) {
        Long createdId = this.orderService.createdOrder(command);
        return createdId;
    }
}

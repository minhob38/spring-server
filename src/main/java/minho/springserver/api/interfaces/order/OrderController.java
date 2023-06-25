package minho.springserver.api.interfaces.order;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.application.order.OrderApplication;
import minho.springserver.api.domain.item.input.ItemCommand;
import minho.springserver.api.domain.order.input.OrderCommand;
import minho.springserver.api.interfaces.item.ItemDto;
import minho.springserver.response.ApiResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/orders")
@RestController
public class OrderController {
    private final OrderApplication orderApplication;

    @PostMapping
    public ApiResponse<OrderDto.CreateOrder.Data> postOrder(@Validated @RequestBody OrderDto.CreateOrder.RequestBody requestBody) {
        // command 만들기
        OrderCommand.CreateOrderCommand command = new OrderCommand.CreateOrderCommand(requestBody);

        // interface -> application
        Long createdId = this.orderApplication.createOrder(command);

        // dto 만들기
        OrderDto.CreateOrder.Data data = new OrderDto.CreateOrder.Data(createdId);

        // 응답 만들기
        return ApiResponse.success(data, "order created");
    }
}

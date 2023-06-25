package minho.springserver.api.domain.order.input;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import minho.springserver.api.interfaces.item.ItemDto;
import minho.springserver.api.interfaces.order.OrderDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderCommand {
    @Getter
    public static class CreateOrderCommand {
        private final Long userId;
        private final String payMethod;
        private final String receiverName;
        private final String receiverPhoneNumber;
        private final String receiverZipcode;
        private final String receiverAddress1;
        private final String receiverAddress2;
        private final String etcMessage;
        private final List<OrderItem> orderItems;

        public CreateOrderCommand(OrderDto.CreateOrder.RequestBody requestBody) {
            this.userId = requestBody.getUserId();
            this.payMethod = requestBody.getPayMethod();
            this.receiverName = requestBody.getReceiverName();
            this.receiverPhoneNumber = requestBody.getReceiverPhoneNumber();
            this.receiverZipcode = requestBody.getReceiverZipcode();
            this.receiverAddress1 = requestBody.getReceiverAddress1();
            this.receiverAddress2 = requestBody.getReceiverAddress2();
            this.etcMessage = requestBody.getEtcMessage();
            this.orderItems = requestBody.getOrderItems()
                    .stream()
                    .map(orderItem -> new OrderItem(orderItem))
                    .collect(Collectors.toList());
        }

        @Getter
        public static class OrderItem {
            private final Long orderItemId;
            private final List<OrderItemOptionGroup> orderItemOptionGroups;

            OrderItem(OrderDto.CreateOrder.OrderItem orderItem) {
                this.orderItemId = orderItem.getOrderItemId();
                this.orderItemOptionGroups = orderItem.getOrderItemOptionGroups()
                        .stream()
                        .map((orderItemOptionGroup -> new OrderItemOptionGroup(orderItemOptionGroup)))
                        .collect(Collectors.toList());
            }
        }

        @Getter
        public static class OrderItemOptionGroup {
            private final String orderItemOptionGroupId;
            private final List<OrderItemOption> orderItemOptions;

            OrderItemOptionGroup(OrderDto.CreateOrder.OrderItemOptionGroup orderItemOptionGroup) {
                this.orderItemOptionGroupId = orderItemOptionGroup.getOrderItemOptionGroupId();
                this.orderItemOptions = orderItemOptionGroup.getOrderItemOptions().stream()
                        .map(orderItemOption -> new OrderItemOption(orderItemOption)).collect(Collectors.toList());
            }
        }

        @Getter
        public static class OrderItemOption {
            private final Long orderItemOptionId;

            OrderItemOption(OrderDto.CreateOrder.OrderItemOption orderItemOption) {
                this.orderItemOptionId = orderItemOption.getOrderItemOptionId();
            }
        }
    }
}

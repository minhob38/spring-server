package minho.springserver.api.interfaces.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class OrderDto {
    public static class CreateOrder {
        @Getter
        @RequiredArgsConstructor
        public static class RequestBody {
            private final Long userId;
            private final String payMethod;
            private final String receiverName;
            private final String receiverPhoneNumber;
            private final String receiverZipcode;
            private final String receiverAddress1;
            private final String receiverAddress2;
            private final String etcMessage;
            private final List<OrderItem> orderItems;
        }

        @Getter
        @RequiredArgsConstructor
        public static class OrderItem {
            private final Long orderItemId;
            private final List<OrderItemOptionGroup> orderItemOptionGroups;
        }

        @Getter
        @RequiredArgsConstructor
        public static class OrderItemOptionGroup {
            private final Long orderItemOptionGroupId;
            private final List<OrderItemOption> orderItemOptions;
        }

        @Getter
//        @RequiredArgsConstructor (final로 하면 deserialize 에러 발생)
        public static class OrderItemOption {
            private Long orderItemOptionId;
        }

        @Getter
        static class Data {
            private final Long orderId;

            Data(Long orderId) {
                this.orderId = orderId;
            }
        }
    }
}

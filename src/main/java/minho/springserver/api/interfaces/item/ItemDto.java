package minho.springserver.api.interfaces.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

// DTO -> Create/Read/Modify/Remove로 정의
public class ItemDto {
    // POST-api/sellers
    public static class CreateItem {
        @Getter
        @RequiredArgsConstructor
        public static class RequestBody {
            private final Long sellerId;

            @NotBlank(message = "seller name is required - '':(X) / ' ':(X) / null:(X)")
            private final String itemName;

            // TODO: 정수 validator 적용
            private final Long itemPrice;

            private final List<ItemOptionGroup> itemOptionGroups;
        }

        @Getter
        @RequiredArgsConstructor
        public static class ItemOptionGroup {
            private final Integer ordering;
            private final String itemOptionGroupName;
            private final List<ItemOption> itemOptions;
        }

        @Getter
        @RequiredArgsConstructor
        public static class ItemOption {
            private final Integer ordering;
            private final String itemOptionName;
            private final Long itemOptionPrice;
        }

        @Getter
        static class Data {
            private final Long itemId;

            Data(Long sellerId) {
                this.itemId = sellerId;
            }
        }
    }

//    static class ReadSeller {
//        @Getter
//        static class Data {
//            private final Long sellerId;
//            private final String sellerName;
//            private final String businessNumber;
//            private final String email;
//            private final ZonedDateTime createdAt;
//            private final ZonedDateTime updatedAt;
//
//            Data(SellerInfo.Seller seller) {
//                this.sellerId = seller.getSellerId();
//                this.sellerName = seller.getSellerName();
//                this.businessNumber = seller.getBusinessNumber();
//                this.email = seller.getEmail();
//                this.createdAt = seller.getCreatedAt();
//                this.updatedAt = seller.getUpdatedAt();
//            }
//        }
//    }
//
//    static class ModifySellerDisabled {
//        @Getter
//        static class Data {
//            private final Long sellerId;
//
//            Data(Long sellerId) {
//                this.sellerId = sellerId;
//            }
//        }
//    }
}

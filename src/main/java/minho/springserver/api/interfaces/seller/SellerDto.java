package minho.springserver.api.interfaces.seller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.seller.SellerInfo;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

// DTO -> Create/Read/Modify/Remove로 정의
public class SellerDto {
    // POST-api/sellers
    static class CreateSeller {
        @Getter
        @RequiredArgsConstructor
        static class RequestBody {
            @NotBlank(message = "seller name is required - '':(X) / ' ':(X) / null:(X)")
            private final String sellerName;

            @NotBlank(message = "business number is required - '':(X) / ' ':(O) / null:(X)")
            private final String businessNumber;

            @NotBlank(message = "email is required - '':(O) / ' ':(O) / null:(X)")
            private final String email;
        }

        @Getter
        static class Data {
            private final Long sellerId;

            Data(Long sellerId) {
                this.sellerId = sellerId;
            }
        }
    }

    static class ReadSeller {
        @Getter
        static class Data {
            private final Long sellerId;
            private final String sellerName;
            private final String businessNumber;
            private final String email;
            private final ZonedDateTime createdAt;
            private final ZonedDateTime updatedAt;

            Data(SellerInfo.Seller seller) {
                this.sellerId = seller.getSellerId();
                this.sellerName = seller.getSellerName();
                this.businessNumber = seller.getBusinessNumber();
                this.email = seller.getEmail();
                this.createdAt = seller.getCreatedAt();
                this.updatedAt = seller.getUpdatedAt();
            }
        }
    }

    static class ModifySellerDisabled {
        @Getter
        static class Data {
            private final Long sellerId;

            Data(Long sellerId) {
                this.sellerId = sellerId;
            }
        }
    }
}

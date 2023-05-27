package minho.springserver.api.interfaces.seller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

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
}

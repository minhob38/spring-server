package minho.springserver.api.domain.seller.input;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import minho.springserver.api.interfaces.seller.SellerDto;

// Command Create/Modify/Remove로 정의
public class SellerCommand {
    @Getter
//    @Builder
    public static class CreateSellerCommand {
        private final String sellerName;
        private final String businessNumber;
        private final String email;

        public CreateSellerCommand(SellerDto.CreateSeller.RequestBody requestBody) {
            this.sellerName = requestBody.getSellerName();
            this.businessNumber = requestBody.getBusinessNumber();
            this.email = requestBody.getEmail();
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class ModifySellerDisabledCommand {
        private final Long sellerId;
    }
}

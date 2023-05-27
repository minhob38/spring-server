package minho.springserver.api.domain.seller.input;

import lombok.Builder;
import lombok.Getter;
import minho.springserver.api.domain.seller.entity.Sellers;

public class SellerCommand {
    @Getter
    @Builder
    public static class CreateSellerCommand {
        private final String sellerName;
        private final String businessNumber;
        private final String email;

        public Sellers toEntity() {
            Sellers seller = Sellers.builder()
                    .sellerName(this.sellerName)
                    .businessNumber(this.businessNumber)
                    .email(this.email)
                    .build();

            return seller;
        }
    }
}

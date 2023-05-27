package minho.springserver.api.domain.seller.input;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.seller.entity.Sellers;

public class SellerCommand {
    @Getter
    @Builder
    public static class CreateSellerCommand {
        private final String sellerName;
        private final String businessNumber;
        private final String email;

        public Sellers toInitialEntity() {
            Sellers initialSeller = Sellers.builder()
                    .sellerName(sellerName)
                    .businessNumber(businessNumber)
                    .email(email)
                    .build();

            return initialSeller;
        }
    }
}

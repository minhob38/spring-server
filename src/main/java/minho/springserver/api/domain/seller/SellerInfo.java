package minho.springserver.api.domain.seller;

import lombok.Builder;
import lombok.Getter;
import minho.springserver.api.domain.seller.entity.Sellers;

public class SellerInfo {
    @Getter
    @Builder
    public static class Seller {
        private final Long id;
        private final String sellerName;
        private final String businessNumber;
        private final String email;
        private final Sellers.Status status;
    }
}

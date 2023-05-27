package minho.springserver.api.domain.seller;

import lombok.Builder;
import lombok.Getter;
import minho.springserver.api.domain.seller.entity.Sellers;

public class SellerInfo {
    @Getter
    @Builder // final 붙여야하나?
    public static class Seller {
        private Long id;
        private String sellerName;
        private String businessNumber;
        private String email;
        private Sellers.Status status;
    }
}

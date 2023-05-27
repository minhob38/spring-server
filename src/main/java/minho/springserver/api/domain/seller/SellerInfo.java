package minho.springserver.api.domain.seller;

import lombok.Builder;
import lombok.Getter;
import minho.springserver.api.domain.seller.entity.Sellers;

import java.time.ZonedDateTime;

public class SellerInfo {
    @Getter
    public static class Seller {
        private final Long sellerId;
        private final String sellerName;
        private final String businessNumber;
        private final String email;
        private final Sellers.Status status;
        private final ZonedDateTime createdAt;
        private final ZonedDateTime updatedAt;

        Seller(Sellers sellers) {
            this.sellerId = sellers.getId();
            this.sellerName = sellers.getSellerName();
            this.businessNumber = sellers.getBusinessNumber();
            this.email = sellers.getEmail();
            this.status = sellers.getStatus();
            this.createdAt = sellers.getCreatedAt();
            this.updatedAt = sellers.getUpdatedAt();
        }
    }
}

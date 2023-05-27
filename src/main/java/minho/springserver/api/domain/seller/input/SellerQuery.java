package minho.springserver.api.domain.seller.input;

import lombok.Builder;
import lombok.Getter;

// Query Read로 정의
public class SellerQuery {

    @Getter
    @Builder
    public static class ReadSellerQuery {
        private final Long sellerId;
    }
}

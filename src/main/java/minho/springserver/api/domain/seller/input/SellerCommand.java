package minho.springserver.api.domain.seller.input;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.seller.entity.Sellers;

// Command Create/Modify/Remove로 정의
public class SellerCommand {
    @Getter
    @Builder
    public static class CreateSellerCommand {
        private final String sellerName;
        private final String businessNumber;
        private final String email;
    }

    @Getter
    @RequiredArgsConstructor
    public static class ModifySellerDisabledCommand {
        private final Long sellerId;
    }
}

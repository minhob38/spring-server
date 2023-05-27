package minho.springserver.api.domain.seller;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.seller.entity.Sellers;
import minho.springserver.api.domain.seller.input.SellerCommand;
import org.springframework.stereotype.Service;

// Service -> Create/Read/Modify/Remove로 정의
@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerCreate sellerCreate;

    Long createSeller(SellerCommand.CreateSellerCommand command) {
        // seller entity 초기화 (command -> entity)
        Sellers initialSeller = command.toInitialEntity();

        // seller 저장
        Long insertedId = this.sellerCreate.insertSeller(initialSeller);

        return insertedId;
    }
////        Sellers seller = this.sellerCreate.
//        Sellers seller = this.sellerCreate.insertSeller(initialSeller)
//        if (seller.isEmpty()) throw new BadRequestException("seller does not exits");
//
//        return SellerInfo.Seller.builder()
//                .id()
//                .sellerName()
//                .businessNumber()
//                .email()
//                .build();
}

//    SellerInfo readSeller(Long sellerId) {
//
//    }
//
//    Long enableSeller(Long sellerId) {
//
//    }
//
//    Long disableSeller(Long sellerId) {
//
//    }


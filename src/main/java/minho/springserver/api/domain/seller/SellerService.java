package minho.springserver.api.domain.seller;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.seller.entity.Sellers;
import minho.springserver.api.domain.seller.input.SellerCommand;
import minho.springserver.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Service -> Create/Read/Modify/Remove로 정의
@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerCreate sellerCreate;
    private final SellerRead sellerRead;

    public Long createSeller(SellerCommand.CreateSellerCommand command) {
        // seller entity 초기화 (command -> entity)
        Sellers initialSeller = command.toEntity();

        // seller 저장
        Long insertedId = this.sellerCreate.insertSeller(initialSeller);

        return insertedId;
    }

    public SellerInfo.Seller readSeller(Long sellerId) throws BadRequestException {
        Optional<Sellers> seller = this.sellerRead.findSeller(sellerId);

        if (seller.isEmpty()) throw new BadRequestException("seller does not exits");

        return new SellerInfo.Seller(seller.get());
    }
}
//
//    Long enableSeller(Long sellerId) {
//
//    }
//
//    Long disableSeller(Long sellerId) {
//
//    }


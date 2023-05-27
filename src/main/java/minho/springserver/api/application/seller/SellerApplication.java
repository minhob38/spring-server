package minho.springserver.api.application.seller;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.seller.SellerInfo;
import minho.springserver.api.domain.seller.SellerService;
import minho.springserver.api.domain.seller.input.SellerCommand;
import minho.springserver.api.domain.seller.input.SellerQuery;
import minho.springserver.exception.BadRequestException;
import org.springframework.stereotype.Component;

// Application -> Create/Read/Modify/Remove로 정의
@Component
@RequiredArgsConstructor
public class SellerApplication {
    private final SellerService sellerService;

    public Long createSeller(SellerCommand.CreateSellerCommand command) {
        Long createdId = this.sellerService.createSeller(command);
        return createdId;
    }

    public SellerInfo.Seller readSeller(SellerQuery.ReadSellerQuery query) throws BadRequestException {
        Long sellerId = query.getSellerId();
        SellerInfo.Seller seller = this.sellerService.readSeller(sellerId);
        return seller;
    }
}

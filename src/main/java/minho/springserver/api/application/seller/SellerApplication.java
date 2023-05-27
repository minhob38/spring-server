package minho.springserver.api.application.seller;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.seller.SellerService;
import minho.springserver.api.domain.seller.input.SellerCommand;
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
}

package minho.springserver.api.infrastructure.seller;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.seller.SellerCreate;
import minho.springserver.api.domain.seller.entity.Sellers;
import org.springframework.stereotype.Component;

// CreateImpl -> insert/update/delete로 정의
@Component
@RequiredArgsConstructor
public class SellerCreateImpl implements SellerCreate {
    private final SellersRepository sellersRepository;

    @Override
    public Long insertSeller(Sellers seller) {
        Sellers savedSeller = this.sellersRepository.save(seller);
        Long savedId = savedSeller.getId();
        return savedId;
    }
}

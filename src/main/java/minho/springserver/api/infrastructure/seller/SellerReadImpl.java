package minho.springserver.api.infrastructure.seller;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.seller.entity.Sellers;
import org.springframework.stereotype.Component;

import java.util.Optional;

// ReadImpl -> find로 정의
@Component
@RequiredArgsConstructor
public class SellerReadImpl implements minho.springserver.api.domain.seller.SellerRead {
    private final SellersRepository sellersRepository;

    @Override
    public Optional<Sellers> findSeller(Long sellerId) {
      Optional<Sellers> seller = this.sellersRepository.findById(sellerId);
      return seller;
    }
}

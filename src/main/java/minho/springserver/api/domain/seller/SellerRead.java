package minho.springserver.api.domain.seller;

import minho.springserver.api.domain.seller.entity.Sellers;

import java.util.Optional;

public interface SellerRead {
    Optional<Sellers> findSeller(Long sellerId);
}

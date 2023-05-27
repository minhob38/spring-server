package minho.springserver.api.domain.seller;

import minho.springserver.api.domain.seller.entity.Sellers;

import java.util.Optional;

public interface SellerCreate {
    Long insertSeller(Sellers seller);
    Optional<Long> disabledSeller(Long sellerId);
}

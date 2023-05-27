package minho.springserver.api.domain.seller;

import minho.springserver.api.domain.seller.entity.Sellers;

public interface SellerCreate {
    Long insertSeller(Sellers seller);
}

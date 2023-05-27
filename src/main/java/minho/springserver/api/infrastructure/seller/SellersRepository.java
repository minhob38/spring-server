package minho.springserver.api.infrastructure.seller;

import minho.springserver.api.domain.seller.entity.Sellers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellersRepository extends JpaRepository<Sellers, Long> {
//    Optional<Sellers> findById(Long sellerId);
}

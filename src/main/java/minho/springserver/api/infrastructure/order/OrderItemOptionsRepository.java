package minho.springserver.api.infrastructure.order;

import minho.springserver.api.domain.order.entity.OrderItemOptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemOptionsRepository extends JpaRepository<OrderItemOptions, Long> {
}

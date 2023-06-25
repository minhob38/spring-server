package minho.springserver.api.infrastructure.order;

import minho.springserver.api.domain.order.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}

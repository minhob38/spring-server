package minho.springserver.api.infrastructure.order;

import minho.springserver.api.domain.order.entity.OrderItemOptionGroups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemOptionGroupsRepository extends JpaRepository<OrderItemOptionGroups, Long> {
}

package minho.springserver.api.infrastructure.item;

import minho.springserver.api.domain.item.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Long> {
}

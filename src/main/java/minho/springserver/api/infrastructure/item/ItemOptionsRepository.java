package minho.springserver.api.infrastructure.item;

import minho.springserver.api.domain.item.entity.ItemOptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionsRepository extends JpaRepository<ItemOptions, Long> {
}

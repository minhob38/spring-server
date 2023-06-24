package minho.springserver.api.infrastructure.item;

import minho.springserver.api.domain.item.entity.ItemOptionGroups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionGroupsRepository extends JpaRepository<ItemOptionGroups, Long> {
}

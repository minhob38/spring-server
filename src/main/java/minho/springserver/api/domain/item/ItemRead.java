package minho.springserver.api.domain.item;

import minho.springserver.api.domain.item.entity.Items;

import java.util.List;
import java.util.Optional;

public interface ItemRead {
    public Optional<Items> findItem(Long itemId);

    public List<ItemInfo.ItemOptionGroup> findItemOptionGroups(Items item);
}

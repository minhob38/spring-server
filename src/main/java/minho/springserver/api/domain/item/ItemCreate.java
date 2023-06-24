package minho.springserver.api.domain.item;

import minho.springserver.api.domain.item.entity.ItemOptionGroups;
import minho.springserver.api.domain.item.entity.ItemOptions;
import minho.springserver.api.domain.item.entity.Items;

import java.util.List;

public interface ItemCreate {
    Long insertItem(Items item);

    List<Long> insertItemOptionGroups(List<ItemOptionGroups> itemOptionGroups);

    List<Long> insertItemOptions(List<ItemOptions> itemOptions);
}

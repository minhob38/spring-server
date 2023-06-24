package minho.springserver.api.domain.item;

import minho.springserver.api.domain.item.entity.Items;

public interface ItemCreate {
    Long insertItem(Items item);
}

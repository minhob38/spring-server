package minho.springserver.api.domain.order;

import minho.springserver.api.domain.item.ItemInfo;
import minho.springserver.api.domain.item.entity.Items;

import java.util.List;
import java.util.Optional;

public interface OrderRead {
    Optional<Items> findItem(Long itemId);

    // lany loading 뒤, join member(private)에 set을 할수 없기에 별도의 함수를 만들어야 합니다.
    List<ItemInfo.ItemOptionGroup> findItemOptionGroups(Items item);
}

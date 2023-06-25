package minho.springserver.api.infrastructure.order;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.ItemInfo;
import minho.springserver.api.domain.item.ItemRead;
import minho.springserver.api.domain.item.entity.Items;
import minho.springserver.api.domain.order.OrderRead;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

// ReadImpl -> find로 정의
@Component
@RequiredArgsConstructor
public class OrderReadImpl implements OrderRead {
    private final ItemRead itemRead;

    @Override
    public Optional<Items> findItem(Long itemId) {
        Optional<Items> item = this.itemRead.findItem(itemId);
        return item;
    }

    // lany loading 뒤, join member(private)에 set을 할수 없기에 별도의 함수를 만들어야 합니다.
    @Override
    public List<ItemInfo.ItemOptionGroup> findItemOptionGroups(Items item) {
        List<ItemInfo.ItemOptionGroup> itemOptionGroups = this.itemRead.findItemOptionGroups(item);
        return itemOptionGroups;
    }
}

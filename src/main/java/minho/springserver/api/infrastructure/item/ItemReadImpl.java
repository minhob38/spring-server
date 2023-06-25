package minho.springserver.api.infrastructure.item;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.ItemInfo;
import minho.springserver.api.domain.item.ItemRead;
import minho.springserver.api.domain.item.entity.ItemOptionGroups;
import minho.springserver.api.domain.item.entity.Items;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// ReadImpl -> find로 정의
@Component
@RequiredArgsConstructor
public class ItemReadImpl implements ItemRead {
    private final ItemsRepository itemsRepository;
    private final ItemOptionGroupsRepository itemOptionGroupsRepository;
    private final ItemOptionsRepository itemOptionsRepository;

    @Override
    public Optional<Items> findItem(Long itemId) {
        Optional<Items> item = this.itemsRepository.findById(itemId);
        return item;
    }

    // lany loading 뒤, join member(private)에 set을 할수 없기에 별도의 함수를 만들어야 합니다.
    @Override
    public List<ItemInfo.ItemOptionGroup> findItemOptionGroups(Items item) {
        List<ItemOptionGroups> itemOptionsGroups = item.getItemOptionGroups();

        List<ItemInfo.ItemOptionGroup> itemOptionGroups = itemOptionsGroups.stream().map(itemOptionGroup -> {
            List<ItemInfo.ItemOption> itemOptions = itemOptionGroup.getItemOptions()
                    .stream()
                    .map(itemOption -> {
                return new ItemInfo.ItemOption(itemOption);
            }).collect(Collectors.toList());

            return new ItemInfo.ItemOptionGroup(itemOptionGroup, itemOptions);
        }).collect(Collectors.toList());

        return itemOptionGroups;
    }
}

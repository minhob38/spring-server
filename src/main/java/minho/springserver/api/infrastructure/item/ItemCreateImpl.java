package minho.springserver.api.infrastructure.item;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.ItemCreate;
import minho.springserver.api.domain.item.entity.ItemOptionGroups;
import minho.springserver.api.domain.item.entity.ItemOptions;
import minho.springserver.api.domain.item.entity.Items;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// CreateImpl -> insert/update/delete로 정의
@Component
@RequiredArgsConstructor
public class ItemCreateImpl implements ItemCreate {
    private final ItemsRepository itemsRepository;
    private final ItemOptionGroupsRepository itemOptionGroupsRepository;
    private final ItemOptionsRepository itemOptionsRepository;

    @Override
    public Long insertItem(Items items) {
        Items savedItem = this.itemsRepository.save(items);
        Long savedId = savedItem.getId();
        return savedId;
    }

    @Override
    public List<Long> insertItemOptionGroups(List<ItemOptionGroups> itemOptionGroups) {
        List<Long> savedIds = itemOptionGroups.stream()
                .map(itemOptionGroup -> {
                    ItemOptionGroups savedItemOptionGroup = this.itemOptionGroupsRepository.save(itemOptionGroup);
                    Long savedId = savedItemOptionGroup.getId();
                    return savedId;
                })
                .collect(Collectors.toList());

        return savedIds;
    }

    @Override
    public List<Long> insertItemOptions(List<ItemOptions> itemOptions) {
        List<Long> savedIds = itemOptions.stream()
                .map(itemOption -> {
                    ItemOptions savedItemOption = this.itemOptionsRepository.save(itemOption);
                    Long savedId = savedItemOption.getId();
                    return savedId;
                })
                .collect(Collectors.toList());

        return savedIds;
    }
}

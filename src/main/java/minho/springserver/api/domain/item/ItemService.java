package minho.springserver.api.domain.item;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.entity.ItemOptionGroups;
import minho.springserver.api.domain.item.entity.Items;
import minho.springserver.api.domain.item.input.ItemCommand;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

// Service -> Create/Read/Modify/Remove로 정의
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemCreate itemCreate;
//    private final ItemRead itemRead;

    @Transactional
    public Long createItem(ItemCommand.CreateItemCommand command) {
        // item entity 초기화 (command -> entity)
        Items initItem = Items.init(command);

        // item 저장
        Long insertedId = this.itemCreate.insertItem(initItem);

        // item option group 저장
        List<ItemOptionGroups> initItemOptionGroups = command.getItemOptionGroups()
                .stream()
                .map(itemOptionGroup -> ItemOptionGroups.init(initItem, itemOptionGroup))
                .collect(Collectors.toList());

        this.itemCreate.insertItemOptionGroups(initItemOptionGroups);

        // item option 저장

        return insertedId;
    }
}

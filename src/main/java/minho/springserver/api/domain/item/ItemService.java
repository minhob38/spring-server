package minho.springserver.api.domain.item;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.entity.ItemOptionGroups;
import minho.springserver.api.domain.item.entity.ItemOptions;
import minho.springserver.api.domain.item.entity.Items;
import minho.springserver.api.domain.item.input.ItemCommand;
import minho.springserver.exception.BadRequestException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Service -> Create/Read/Modify/Remove로 정의
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemCreate itemCreate;
    private final ItemRead itemRead;

    @Transactional
    public Long createItem(ItemCommand.CreateItemCommand command) {
        // item entity 초기화 (command -> entity) 및  // item 저장
        Items initItem = Items.init(command);
        Long insertedItemId = this.itemCreate.insertItem(initItem);

        // item option group entity 및 item option entity 초기화 (command -> entity)
        List<ItemOptions> initItemOptions = new ArrayList<>();
        List<ItemOptionGroups> initItemOptionGroups = command.getItemOptionGroups()
                .stream()
                .map(itemOptionGroup -> {
                    ItemOptionGroups initItemOptionGroup = ItemOptionGroups.init(initItem, itemOptionGroup);
                    itemOptionGroup.getItemOptions().forEach(itemOption -> {
                        ItemOptions initItemOption = ItemOptions.init(initItemOptionGroup, itemOption);
                        initItemOptions.add(initItemOption);
                    });
                    return initItemOptionGroup;
                })
                .collect(Collectors.toList());

        // item option group 및 item option 저장
        List<Long> insertedItemOptionGroupIds = this.itemCreate.insertItemOptionGroups(initItemOptionGroups);
        List<Long> insertedItemOptionIds =  this.itemCreate.insertItemOptions(initItemOptions);

        return insertedItemId;
    }

    public ItemInfo.Item readItem(Long itemId) throws BadRequestException {
        Optional<Items> item = this.itemRead.findItem(itemId);

        if (item.isEmpty()) throw new BadRequestException("item does not exits");

        List<ItemInfo.ItemOptionGroup> itemOptionGroups = this.itemRead.findItemOptionGroups(item.get());

        return new ItemInfo.Item(item.get(), itemOptionGroups);
    }
}

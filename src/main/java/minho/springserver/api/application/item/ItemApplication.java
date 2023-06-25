package minho.springserver.api.application.item;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.ItemInfo;
import minho.springserver.api.domain.item.ItemService;
import minho.springserver.api.domain.item.input.ItemCommand;
import minho.springserver.api.domain.item.input.ItemQuery;
import minho.springserver.exception.BadRequestException;
import org.springframework.stereotype.Component;

// Application -> Create/Read/Modify/Remove로 정의
@Component
@RequiredArgsConstructor
public class ItemApplication {
    private final ItemService itemService;

    public Long createItem(ItemCommand.CreateItemCommand command) {
        Long createdId = this.itemService.createItem(command);
        return createdId;
    }

    public ItemInfo.Item readItem(ItemQuery.ReadItemQuery query) throws BadRequestException {
        Long itemId = query.getItemId();
        ItemInfo.Item item = this.itemService.readItem(itemId);
        return item;
    }
}

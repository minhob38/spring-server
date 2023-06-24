package minho.springserver.api.application.item;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.ItemService;
import minho.springserver.api.domain.item.input.ItemCommand;
import org.springframework.stereotype.Component;

// Application -> Create/Read/Modify/Remove로 정의
@Component
@RequiredArgsConstructor
public class ItemApplication {
    private final ItemService itemService;

    public Long createSeller(ItemCommand.CreateItemCommand command) {
        Long createdId = this.itemService.createItem(command);
        return createdId;
    }
}

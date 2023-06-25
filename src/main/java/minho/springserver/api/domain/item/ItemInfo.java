package minho.springserver.api.domain.item;

import lombok.Getter;
import minho.springserver.api.domain.item.entity.Items;
import minho.springserver.api.domain.seller.entity.Sellers;

import java.time.ZonedDateTime;

public class ItemInfo {
    @Getter
    public static class Item {
        private final Long itemId;
        private final Long sellerId;
        private final String itemName;
        private final Long itemPrice;
        private final Items.Status status;
        private final ZonedDateTime createdAt;
        private final ZonedDateTime updatedAt;

        Item(Items items) {
            this.itemId = items.getId();
            this.sellerId = items.getSellerId();
            this.itemName = items.getItemName();
            this.itemPrice = items.getItemPrice();
            this.status = items.getStatus();
            this.createdAt = items.getCreatedAt();
            this.updatedAt = items.getUpdatedAt();
        }
    }
}

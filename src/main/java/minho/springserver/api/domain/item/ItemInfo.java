package minho.springserver.api.domain.item;

import lombok.Getter;
import minho.springserver.api.domain.item.entity.ItemOptionGroups;
import minho.springserver.api.domain.item.entity.ItemOptions;
import minho.springserver.api.domain.item.entity.Items;
import java.time.ZonedDateTime;
import java.util.List;

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

        private final List<ItemOptionGroup> itemOptionGroups;

        Item(Items items, List<ItemOptionGroup> itemOptionGroups) {
            this.itemId = items.getId();
            this.sellerId = items.getSellerId();
            this.itemName = items.getItemName();
            this.itemPrice = items.getItemPrice();
            this.status = items.getStatus();
            this.createdAt = items.getCreatedAt();
            this.updatedAt = items.getUpdatedAt();
            this.itemOptionGroups = itemOptionGroups;
        }
    }

    @Getter
    public static class ItemOptionGroup {
        private final Long itemId;
        private final Long itemOptionGroupId;
        private final Integer ordering;
        private final String itemOptionGroupName;

        private final List<ItemOption> itemOptions;

        public ItemOptionGroup(ItemOptionGroups itemOptionGroup, List<ItemOption> itemOptions) {
            this.itemId = itemOptionGroup.getItem().getId();
            this.itemOptionGroupId = itemOptionGroup.getId();
            this.ordering = itemOptionGroup.getOrdering();
            this.itemOptionGroupName = itemOptionGroup.getItemOptionGroupName();
            this.itemOptions = itemOptions;
        }
    }

    @Getter
    public static class ItemOption {
        private final Long itemOptionId;
        private final Long itemOptionGroupId;
        private final Integer ordering;
        private final String itemOptionName;
        private Long itemOptionPrice;

        public ItemOption(ItemOptions itemOption) {
            this.itemOptionId = itemOption.getId();
            this.itemOptionGroupId = itemOption.getItemOptionGroup().getId();
            this.ordering = itemOption.getOrdering();
            this.itemOptionName = itemOption.getItemOptionName();
            this.itemOptionPrice = itemOption.getItemOptionPrice();
        }
    }
}

package minho.springserver.api.domain.item.input;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import minho.springserver.api.interfaces.item.ItemDto;
import minho.springserver.api.interfaces.seller.SellerDto;

import java.util.List;
import java.util.stream.Collectors;

public class ItemCommand {
    @Getter
//    @Builder
    public static class CreateItemCommand {
        private final String itemName;
        private final Long itemPrice;
        private final List<ItemOptionGroup> itemOptionGroups;

        public CreateItemCommand(ItemDto.CreateItem.RequestBody requestBody) {
            this.itemName = requestBody.getItemName();
            this.itemPrice = requestBody.getItemPrice();
            this.itemOptionGroups = requestBody.getItemOptionGroups()
                    .stream()
                    .map(itemOptionGroup -> new ItemOptionGroup(itemOptionGroup))
                    .collect(Collectors.toList());
        }

        static class ItemOptionGroup {
            private final Integer ordering;
            private final String itemOptionGroupName;
            private final List<ItemOption> itemOptions;

            public ItemOptionGroup(ItemDto.CreateItem.ItemOptionGroup itemOptionGroup) {
                this.ordering = itemOptionGroup.getOrdering();
                this.itemOptionGroupName = itemOptionGroup.getItemOptionGroupName();
                this.itemOptions = itemOptionGroup.getItemOptions()
                        .stream()
                        .map(itemOption -> {
                            Integer ordering = itemOption.getOrdering();
                            String itemOptionName = itemOption.getItemOptionName();
                            Long itemOptionPrice = itemOption.getItemOptionPrice();
                            return new ItemOption(ordering, itemOptionName, itemOptionPrice);
                        }).collect(Collectors.toList());
            }
        }

        static class ItemOption {
            private final Integer ordering;
            private final String itemOptionName;
            private final Long itemOptionPrice;

            public ItemOption(Integer ordering, String itemOptionName, Long itemOptionPrice) {
                this.ordering = ordering;
                this.itemOptionName = itemOptionName;
                this.itemOptionPrice = itemOptionPrice;
            }
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class ModifySellerDisabledCommand {
        private final Long sellerId;
    }
}

package minho.springserver.api.interfaces.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.ItemInfo;
import minho.springserver.api.domain.item.entity.Items;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

// DTO -> Create/Read/Modify/Remove로 정의
public class ItemDto {
    // POST-api/sellers
    public static class CreateItem {
        @Getter
        @RequiredArgsConstructor
        public static class RequestBody {
            private final Long sellerId;

            @NotBlank(message = "item name is required - '':(X) / ' ':(X) / null:(X)")
            private final String itemName;

            // TODO: 정수 validator 적용
            private final Long itemPrice;

            // TODO: 중첩객체 validation
            private final List<ItemOptionGroup> itemOptionGroups;
        }

        @Getter
        @RequiredArgsConstructor
        public static class ItemOptionGroup {
            private final Integer ordering;
            private final String itemOptionGroupName;
            private final List<ItemOption> itemOptions;
        }

        @Getter
        @RequiredArgsConstructor
        public static class ItemOption {
            private final Integer ordering;
            private final String itemOptionName;
            private final Long itemOptionPrice;
        }

        @Getter
        static class Data {
            private final Long itemId;

            Data(Long sellerId) {
                this.itemId = sellerId;
            }
        }
    }

    static class ReadItem {
        @Getter
        static class Data {
            private final Long itemId;
            private final Long sellerId;
            private final String itemName;
            private final Long itemPrice;
            private final Items.Status status;
            private final ZonedDateTime createdAt;
            private final ZonedDateTime updatedAt;

            private final List<ItemOptionGroup> itemOptionGroups;

            Data(ItemInfo.Item item) {
                this.itemId = item.getItemId();
                this.sellerId = item.getSellerId();
                this.itemName = item.getItemName();
                this.itemPrice = item.getItemPrice();
                this.status = item.getStatus();
                this.createdAt = item.getCreatedAt();
                this.updatedAt = item.getUpdatedAt();
                this.itemOptionGroups = item.getItemOptionGroups()
                        .stream()
                        .map(itemOptionGroup -> {
                            return new ItemOptionGroup(itemOptionGroup);
                        }).collect(Collectors.toList());
            }
        }

        @Getter
        public static class ItemOptionGroup {
            private final Long itemId;
            private final Long itemOptionGroupId;
            private final Integer ordering;
            private final String itemOptionGroupName;
            private final List<ItemOption> itemOptions;

            public ItemOptionGroup(ItemInfo.ItemOptionGroup itemOptionGroup) {
                this.itemId = itemOptionGroup.getItemId();
                this.itemOptionGroupId = itemOptionGroup.getItemOptionGroupId();
                this.ordering = itemOptionGroup.getOrdering();
                this.itemOptionGroupName = itemOptionGroup.getItemOptionGroupName();
                this.itemOptions = itemOptionGroup.getItemOptions()
                        .stream()
                        .map(itemOption -> {
                            return new ItemOption(itemOption);
                        }).collect(Collectors.toList());
            }
        }

        @Getter
        public static class ItemOption {
            private final Long itemOptionId;
            private final Long itemOptionGroupId;
            private final Integer ordering;
            private final String itemOptionName;
            private Long itemOptionPrice;

            public ItemOption(ItemInfo.ItemOption itemOption) {
                this.itemOptionId = itemOption.getItemOptionId();
                this.itemOptionGroupId = itemOption.getItemOptionGroupId();
                this.ordering = itemOption.getOrdering();
                this.itemOptionName = itemOption.getItemOptionName();
                this.itemOptionPrice = itemOption.getItemOptionPrice();
            }
        }
    }
}

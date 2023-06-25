package minho.springserver.api.domain.item.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import minho.springserver.api.domain.item.input.ItemCommand;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

//@ToString
@Getter
@Entity
@NoArgsConstructor
@Table(name = "item_options", schema = "public")
public class ItemOptions extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_option_group_id")
    private ItemOptionGroups itemOptionGroup;
    private Integer ordering;
    private String itemOptionName;
    private Long itemOptionPrice;

    @Builder
    public ItemOptions (
            ItemOptionGroups itemOptionGroup,
            Integer ordering,
            String itemOptionName,
            Long itemOptionPrice
    ) {
        if (itemOptionGroup == null) throw new RuntimeException("no item option group");
        if (ordering == null) throw new RuntimeException("no ordering");
        if (StringUtils.isBlank(itemOptionName)) throw new RuntimeException("no item option name");
        if (itemOptionPrice == null) throw new RuntimeException("no item option price");

        this.itemOptionGroup = itemOptionGroup;
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
    }

    public static ItemOptions init(ItemOptionGroups itemOptionGroup, ItemCommand.CreateItemCommand.ItemOption command) {
        ItemOptions itemOption = ItemOptions.builder()
                .itemOptionGroup(itemOptionGroup)
                .ordering(command.getOrdering())
                .itemOptionName(command.getItemOptionName())
                .itemOptionPrice(command.getItemOptionPrice())
                .build();

        return itemOption;
    }
}

package minho.springserver.api.domain.item.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import minho.springserver.api.domain.item.input.ItemCommand;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "item_option_groups", schema = "public")
public class ItemOptionGroups extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id") // 실제 foreign key column 이름
    private Items item;
    private Integer ordering;
    private String itemOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOptionGroup", cascade = CascadeType.PERSIST)
    private List<ItemOptions> itemOptions = new ArrayList<ItemOptions>();

    @Builder
    public ItemOptionGroups(Items item, Integer ordering, String itemOptionGroupName) {
        if (item == null) throw new RuntimeException("no item");
        if (ordering == null) throw new RuntimeException("no odering");
        if (StringUtils.isBlank(itemOptionGroupName)) {
            throw new RuntimeException("no item option group name");
        }

        this.item = item;
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
    }

    public static ItemOptionGroups init(Items item, ItemCommand.CreateItemCommand.ItemOptionGroup command) {
        ItemOptionGroups itemOptionGroups = ItemOptionGroups.builder()
                .item(item)
                .ordering(command.getOrdering())
                .itemOptionGroupName(command.getItemOptionGroupName())
                .build();

        return itemOptionGroups;
    }

//    public ItemOptionGroups addItemOption(ItemOptions itemOption) {
//        this.itemOptions.add(itemOption);
//        return this;
//    }
}

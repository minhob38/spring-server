package minho.springserver.api.domain.item.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
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
    @JoinColumn(name = "item_id")
    private Items item;
    private Integer ordering;
    private String itemOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOptionGroup", cascade = CascadeType.PERSIST)
    private List<ItemOptions> itemOptionList = Lists.newArrayList();

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

    public ItemOptionGroups addItemOption(ItemOptions itemOption) {
        this.itemOptionList.add(itemOption);
        return this;
    }
}

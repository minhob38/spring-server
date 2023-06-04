package minho.springserver.api.domain.item.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "items", schema = "public")
public class Items extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sellerId;
    private String itemName;
    private Long itemPrice;

    // mappedBy는 foreign key member 이름 (@ManyToOne이 붙은 member 이름)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.PERSIST)
    private List<ItemOptionGroups> itemOptionGroups = new ArrayList<ItemOptionGroups>();
    @Enumerated(EnumType.STRING)
    private Status status;

    @RequiredArgsConstructor
    public enum Status {
        PREPARING_FOR_SALE("판매준비중"),
        ON_SALE("판매중"),
        END_OF_SALE("판매종료");

        private final String description;
    }

    @Builder
    public Items(Long sellerId, String itemName, Long itemPrice) {
        if (sellerId == null) throw new RuntimeException("no seller id");
        if (StringUtils.isEmpty(itemName)) throw new RuntimeException("no item name");
        if (itemPrice == null) throw new RuntimeException("no item price");

        this.sellerId = sellerId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.status = Status.PREPARING_FOR_SALE;
    }

    public void changePreparingForSale() {
        this.status = Status.PREPARING_FOR_SALE;
    }

    public void changeOnSale() {
        this.status = Status.ON_SALE;
    }

    public void changeEndOfSale() {
        this.status = Status.END_OF_SALE;
    }

//    public static Items init(ItemCommand.CreateItemCommand command) {
//        Items item = Items.builder()
//                .sellerId(command.getSellerId())
//                .itemName(command.getItemName())
//                .itemPrice(command.getItemPrice())
//                .build();
//
//        return item;
//    }
}

package minho.springserver.api.domain.order.entity;

import lombok.*;
import minho.springserver.api.domain.item.ItemInfo;
import minho.springserver.api.domain.item.entity.Items;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItems extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    private Integer orderCount;
    private Long sellerId;
    private Long itemId;
    private String itemName;
    private Long itemPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItems", cascade = CascadeType.PERSIST)
    private List<OrderItemOptionGroups> orderItemOptionGroups = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @RequiredArgsConstructor
    public enum DeliveryStatus {
        BEFORE_DELIVERY("배송전"),
        DELIVERY_PREPARE("배송준비중"),
        DELIVERING("배송중"),
        COMPLETE_DELIVERY("배송완료");

        private final String description;
    }

    @Builder
    public OrderItems(
            Orders orders,
            Integer orderCount,
            Long sellerId,
            Long itemId,
            String itemName,
            String itemToken,
            Long itemPrice
    ) {
        if (orders == null) throw new RuntimeException("no order");
        if (orderCount == null) throw new RuntimeException("no order count");
        if (sellerId == null) throw new RuntimeException("not seller id");
        if (itemId == null) throw new RuntimeException("no item id");
        if (StringUtils.isEmpty(itemName)) throw new RuntimeException("no item name");
        if (itemPrice == null) throw new RuntimeException("no item price");

        this.orders = orders;
        this.orderCount = orderCount;
        this.sellerId = sellerId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.deliveryStatus = DeliveryStatus.BEFORE_DELIVERY;
    }

    public static OrderItems init(Orders order, Integer orderCount ,Items item) {
        return OrderItems.builder()
                .orders(order)
                .orderCount(orderCount)
                .sellerId(item.getSellerId())
                .itemId(item.getId())
                .itemName(item.getItemName())
                .itemPrice(item.getItemPrice())
                .build();
    }
}
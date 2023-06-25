package minho.springserver.api.domain.order.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItemOptionGroups extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItems orderItems;
    private Integer ordering;
    private String itemOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItemOptionGroup", cascade = CascadeType.PERSIST)
    private List<OrderItemOptions> orderItemOptionList = new ArrayList<>();

    @Builder
    public OrderItemOptionGroups(
            OrderItems orderItems,
            Integer ordering,
            String itemOptionGroupName
    ) {
        if (orderItems == null) throw new RuntimeException("no order item");
        if (ordering == null) throw new RuntimeException("no ordering");
        if (StringUtils.isEmpty(itemOptionGroupName)) throw new RuntimeException("no item option group name");

        this.orderItems = orderItems;
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
    }
}
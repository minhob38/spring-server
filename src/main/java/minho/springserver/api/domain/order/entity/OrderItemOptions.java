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
public class OrderItemOptions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_item_option_group_id")
    private OrderItemOptionGroups orderItemOptionGroup;
    private Integer ordering;
    private String itemOptionName;
    private Long itemOptionPrice;

    @Builder
    public OrderItemOptions(
            OrderItemOptionGroups orderItemOptionGroup,
            Integer ordering,
            String itemOptionName,
            Long itemOptionPrice) {
        if (orderItemOptionGroup == null) throw new RuntimeException("no order item option group");
        if (ordering == null) throw new RuntimeException("no ordering");
        if (StringUtils.isEmpty(itemOptionName)) throw new RuntimeException("no item option name");
        if (itemOptionPrice == null) throw new RuntimeException("no item option price");

        this.orderItemOptionGroup = orderItemOptionGroup;
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
    }
}
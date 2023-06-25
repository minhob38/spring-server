package minho.springserver.api.domain.order.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.entity.Items;
import minho.springserver.api.domain.item.input.ItemCommand;
import minho.springserver.api.domain.order.input.OrderCommand;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "orders", schema = "public")
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String payMethod;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders", cascade = CascadeType.PERSIST)
    private List<minho.springserver.api.domain.order.entity.OrderItems> OrderItems = new ArrayList<>();

    @Embedded
    private DeliveryFragment deliveryFragment;

    private ZonedDateTime orderedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        INIT("주문시작"),
        ORDER_COMPLETE("주문완료"),
        DELIVERY_PREPARE("배송준비"),
        IN_DELIVERY("배송중"),
        DELIVERY_COMPLETE("배송완료");

        private final String description;
    }

    @Builder
    public Orders(
            Long userId,
            String payMethod,
            DeliveryFragment deliveryFragment
    ) {
        if (userId == null) throw new RuntimeException("no user id");
        if (StringUtils.isEmpty(payMethod)) throw new RuntimeException("no pay method");
        if (deliveryFragment == null) throw new RuntimeException("no delivery fragment");

        this.userId = userId;
        this.payMethod = payMethod;
        this.deliveryFragment = deliveryFragment;
        this.orderedAt = ZonedDateTime.now();
        this.status = Status.INIT;
    }

    @Getter
    @Embeddable
    @NoArgsConstructor
    static class DeliveryFragment {
       private String receiverName;
       private String receiverPhoneNumber;
       private String receiverZipcode;
       private String receiverAddress1;
       private String receiverAddress2;
       private String etcMessage;

       @Builder
       public DeliveryFragment(
               String receiverName,
               String receiverPhoneNumber,
               String receiverZipcode,
               String receiverAddress1,
               String receiverAddress2,
               String etcMessage
       ) {
           if (StringUtils.isEmpty(receiverName)) throw new RuntimeException("DeliveryFragment receiverName");
           if (StringUtils.isEmpty(receiverPhoneNumber)) throw new RuntimeException("DeliveryFragment receiverPhone");
           if (StringUtils.isEmpty(receiverZipcode)) throw new RuntimeException("DeliveryFragment receiverZipcode");
           if (StringUtils.isEmpty(receiverAddress1)) throw new RuntimeException("DeliveryFragment receiverAddress1");
           if (StringUtils.isEmpty(receiverAddress2)) throw new RuntimeException("DeliveryFragment receiverAddress2");
           if (StringUtils.isEmpty(etcMessage)) throw new RuntimeException("DeliveryFragment etcMessage");

           this.receiverName = receiverName;
           this.receiverPhoneNumber = receiverPhoneNumber;
           this.receiverZipcode = receiverZipcode;
           this.receiverAddress1 = receiverAddress1;
           this.receiverAddress2 = receiverAddress2;
           this.etcMessage = etcMessage;
       }
   }

    public static Orders init(OrderCommand.CreateOrderCommand command) {
        DeliveryFragment deliveryFragment =  DeliveryFragment.builder()
                .receiverName(command.getReceiverName())
                .receiverPhoneNumber(command.getReceiverPhoneNumber())
                .receiverZipcode(command.getReceiverZipcode())
                .receiverAddress1(command.getReceiverAddress1())
                .receiverAddress2(command.getReceiverAddress2())
                .etcMessage(command.getEtcMessage())
                .build();

        Orders order = Orders.builder()
                .userId(command.getUserId())
                .payMethod(command.getPayMethod())
                .deliveryFragment(deliveryFragment)
                .build();

        return order;
    }
}

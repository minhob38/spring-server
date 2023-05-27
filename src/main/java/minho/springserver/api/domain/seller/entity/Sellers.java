package minho.springserver.api.domain.seller.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.seller.input.SellerCommand;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;
@Getter
// @Setter data integrity를 위해 setter를 사용하지 않는게 좋으며, 따라서 생성자에서 member들을 할당해줍니다.
@Entity
@NoArgsConstructor
@Table(name = "sellers", schema = "public")
public class Sellers extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sellerName;
    private String businessNumber;
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    @RequiredArgsConstructor
    public enum Status {
        ENABLED("활성"), DISABLED("비활성");
        private final String description;
    }

    @Builder
    public Sellers(String sellerName, String businessNumber, String email) {
        if (StringUtils.isEmpty(sellerName)) throw new RuntimeException("no seller name");
        if (StringUtils.isEmpty(businessNumber)) throw new RuntimeException("no business number");
        if (StringUtils.isEmpty(email)) throw new RuntimeException("no email");

        this.sellerName = sellerName;
        this.businessNumber = businessNumber;
        this.email = email;
        this.status = Status.ENABLED;
    }

    public void enable() {
        this.status = Status.ENABLED;
    }

    public void disable() {
        this.status = Status.DISABLED;
    }

    // instance 생성함수는 해당 class 안에 있어야 응집도가 높아 좋습니다.
    public static Sellers init(SellerCommand.CreateSellerCommand command) {
        Sellers seller = Sellers.builder()
                .sellerName(command.getSellerName())
                .businessNumber(command.getBusinessNumber())
                .email(command.getEmail())
                .build();

        return seller;
    }
}

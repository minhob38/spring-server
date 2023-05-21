package minho.springserver.api.domain.auth;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
@Getter @Setter
@Entity
@Table(name = "users", schema = "public")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
    @Override
    public  String toString() {
        return "id: "  + this.getId()
                + "\nemail: " +  this.getEmail()
                + "\npassword: " +  this.getPassword()
                + "\ncreatedAt: " +  this.getCreatedAt()
                + "\nupdatedAt: " +  this.getUpdatedAt();
    }
}
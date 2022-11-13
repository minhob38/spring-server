package minho.springserver.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "users", schema = "public")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Override
    public  String toString() {
        return "id: "  + this.getId()
                + "\nemail: " +  this.getEmail()
                + "\npassword: " +  this.getPassword()
                + "\ncreatedAt: " +  this.getCreatedAt()
                + "\nupdatedAt: " +  this.getUpdatedAt();
    }
}

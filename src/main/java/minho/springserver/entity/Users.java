package minho.springserver.entity;

import javax.persistence.*;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String createdAt;
    private String updatedAt;
}

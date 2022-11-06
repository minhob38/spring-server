package minho.springserver.dto;

import lombok.Data;

@Data
public class PostLoginForm {
    private String email;
    private String password;
}

package minho.springserver.service;

import org.springframework.stereotype.Component;

@Component
public class AuthService {

    public boolean getIsUser() {
        return false;
    }

    public String createHash(String password) {
        return "hash";
    }

    public String createToken(String email) {
        return "token";
    }
}

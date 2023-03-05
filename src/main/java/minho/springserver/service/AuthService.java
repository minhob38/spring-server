package minho.springserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    private final minho.springserver.domain.auth.AuthService.UsersRepository usersRepository;

    @Autowired
    public AuthService(minho.springserver.domain.auth.AuthService.UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public String createHash(String password) {
        return password;
    }

    public String createToken(String email) {
        return "token";
    }

    public boolean checkIsMatchPassword(String password, String hash) {
        if (password.equals(hash)) return true;
        return false;
    }
}

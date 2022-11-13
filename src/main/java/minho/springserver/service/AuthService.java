package minho.springserver.service;

import minho.springserver.dao.UsersRepository;
import minho.springserver.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthService {
    private final UsersRepository usersRepository;

    @Autowired
    public AuthService(UsersRepository usersRepository) {
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

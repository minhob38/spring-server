package minho.springserver.service;

import minho.springserver.dao.UsersRepository;
import minho.springserver.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    private final UsersRepository usersRepository;

    @Autowired
    public AuthService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public boolean getIsUser(String email) {
        Users user = this.usersRepository.findByEmail(email);
        return false;
    }

    public String createHash(String password) {
        return "hash";
    }

    public String createToken(String email) {
        return "token";
    }
}

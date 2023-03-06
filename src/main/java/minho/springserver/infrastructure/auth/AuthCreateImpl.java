package minho.springserver.infrastructure.auth;

import lombok.RequiredArgsConstructor;
import minho.springserver.domain.auth.AuthCreate;
import minho.springserver.domain.auth.AuthRead;
import minho.springserver.entity.Users;

import java.util.Optional;

@RequiredArgsConstructor
public class AuthCreateImpl implements AuthCreate {
    private final UsersRepository usersRepository;

    @Override
    public String createHash(String password) {
        return "hash" + password;
    }

    @Override
    public Long saveUser(String email, String password) {
        Long id = this.usersRepository.save(email, password);
        return id;
    }
}

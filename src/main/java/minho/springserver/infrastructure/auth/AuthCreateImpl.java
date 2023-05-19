package minho.springserver.infrastructure.auth;

import lombok.RequiredArgsConstructor;
import minho.springserver.domain.auth.AuthCreate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthCreateImpl implements AuthCreate {
    private final UsersRepository usersRepository;

    @Override
    public String createHash(String password) {
        return password;
    }

    @Override
    public Long saveUser(String email, String password) {
        Long id = this.usersRepository.create(email, password);
        return id;
    }

    @Override
    public void updatePassword(Long userId, String hash) {
        this.usersRepository.updatePassword(userId, hash);
    }
}

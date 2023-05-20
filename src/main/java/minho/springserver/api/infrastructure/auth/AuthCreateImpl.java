package minho.springserver.api.infrastructure.auth;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.auth.AuthCreate;
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
        Long updatedId = this.usersRepository.create(email, password);
        return updatedId;
    }

    @Override
    public Long updatePassword(Long userId, String hash) {
        Long updatedId = this.usersRepository.updatePassword(userId, hash);
        return updatedId;
    }

    @Override
    public Long deleteUser(Long userId) {
        Long deletedId = this.usersRepository.delete(userId);
        return deletedId;
    }
}

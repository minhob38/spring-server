package minho.springserver.api.infrastructure.auth;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.auth.AuthRead;
import minho.springserver.entity.Users;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthReadImpl implements AuthRead {
    private final UsersRepository usersRepository;

    @Override
    public Optional<Users> findUserByEmail(String email) {
        Optional<Users> user =  this.usersRepository.findByEmail(email);
        return user;
    }

    @Override
    public Optional<Users> findUserById(Long userId) {
        Optional<Users> user =  this.usersRepository.findById(userId);
        return user;
    }

    @Override
    public boolean checkIsMatchPassword(String password, String hash) {
        if (password.equals(hash)) return true;
        return false;
    }
}
package minho.springserver.api.domain.auth;

import minho.springserver.entity.Users;

import java.util.Optional;

public interface AuthRead {
    Optional<Users> findUserByEmail(String email);
    Optional<Users> findUserById(Long userId);
    boolean checkIsMatchPassword(String password, String hash);
}

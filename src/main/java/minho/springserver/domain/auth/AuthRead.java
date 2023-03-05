package minho.springserver.domain.auth;

import minho.springserver.entity.Users;

import java.util.Optional;

public interface AuthRead {
    Optional<Users> findUserByEmail(String email);
}

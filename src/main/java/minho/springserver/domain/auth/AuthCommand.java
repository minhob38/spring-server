package minho.springserver.domain.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class AuthCommand {

    @Getter
    @RequiredArgsConstructor
    public static class SignUpCommand {
        private final String email;
        private final String password;
    }
}

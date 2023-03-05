package minho.springserver.domain.auth;

import lombok.RequiredArgsConstructor;

public class AuthCommand {

    @RequiredArgsConstructor
    public static class SignUpCommand {
        private final String email;
        private final String password;
    }
}

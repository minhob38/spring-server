package minho.springserver.domain.auth;

import lombok.RequiredArgsConstructor;

public class AuthInfo {

    @RequiredArgsConstructor
    public static class SignupInfo {
        private final String accessToken;
    }
}

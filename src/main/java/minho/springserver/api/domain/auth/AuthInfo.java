package minho.springserver.api.domain.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class AuthInfo {

    @RequiredArgsConstructor
    @Getter
    public static class SignupInfo {
        private final Long userId;
    }

    @RequiredArgsConstructor
    @Getter
    public static class SigninInfo {
        private final Long userId;
    }

    @RequiredArgsConstructor
    @Getter
    public static class UserInfo {
        private final Long userId;
        private final String email;
    }
}

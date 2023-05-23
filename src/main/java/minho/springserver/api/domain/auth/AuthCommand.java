package minho.springserver.api.domain.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Command Create/Modify/Remove로 정의
public class AuthCommand {

    @Getter
    @RequiredArgsConstructor
    public static class SignupCommand {
        private final String email;
        private final String password;
    }

    @Getter
    @RequiredArgsConstructor
    public static class SigninCommand {
        private final String email;
        private final String password;
    }

    @Getter
    @RequiredArgsConstructor
    public static class ModifyPasswordCommand {
        private final Long userId;
        private final String newPassword;
        private final String currentPassword;
    }

    @Getter
    @RequiredArgsConstructor
    public static class SignoutCommand {
        private final Long userId;
    }
}



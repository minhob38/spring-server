package minho.springserver.domain.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
    public static class UpdatePasswordCommand {
        private final String email;
        private final String newPassword;
        private final String currentPassword;
    }

}



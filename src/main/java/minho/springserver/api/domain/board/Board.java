package minho.springserver.api.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Board {

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
        private final Long userId;
        private final String newPassword;
        private final String currentPassword;
    }

    @Getter
    @RequiredArgsConstructor
    public static class ReadMeCommand {
        private final Long userId;
    }

    @Getter
    @RequiredArgsConstructor
    public static class SignoutCommand {
        private final Long userId;
    }
}



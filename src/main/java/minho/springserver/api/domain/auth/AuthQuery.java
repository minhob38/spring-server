package minho.springserver.api.domain.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Query Read로 정의
public class AuthQuery {
    @Getter
    @RequiredArgsConstructor
    public static class ReadMeQuery {
        private final Long userId;
    }
}



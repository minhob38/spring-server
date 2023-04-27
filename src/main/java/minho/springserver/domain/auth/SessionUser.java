package minho.springserver.domain.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SessionUser {
    private final Long userId;
}

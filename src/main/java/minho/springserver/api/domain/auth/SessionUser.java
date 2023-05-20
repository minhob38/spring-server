package minho.springserver.api.domain.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class SessionUser {
    private final Long userId;
}

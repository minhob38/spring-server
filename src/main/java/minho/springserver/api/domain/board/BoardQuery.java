package minho.springserver.api.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

// Query Read로 정의
public class BoardQuery {

    @Getter
    @ToString
    @RequiredArgsConstructor
    public static class ReadPostQuery {
        private final Long postId;
    }
}

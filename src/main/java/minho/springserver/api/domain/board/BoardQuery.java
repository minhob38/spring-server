package minho.springserver.api.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class BoardQuery {

    @Getter
    @ToString
    @RequiredArgsConstructor
    public static class FindPostQuery {
        private final Long postId;
    }
}

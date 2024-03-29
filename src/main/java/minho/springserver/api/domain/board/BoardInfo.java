package minho.springserver.api.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class BoardInfo {

    @ToString
    @Getter
    @RequiredArgsConstructor
    public static class PostInfo {
        private final Long postId;
        private final String author;
        private final String title;
        private final String content;
    }
}

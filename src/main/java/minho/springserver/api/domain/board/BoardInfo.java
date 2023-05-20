package minho.springserver.api.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class BoardInfo {

    @Getter
    @RequiredArgsConstructor
    public static class PostInfo {
        private final Long postId;
        private final String author;
        private final String title;
        private final String content;
    }
}

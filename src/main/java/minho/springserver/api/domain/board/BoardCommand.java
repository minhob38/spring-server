package minho.springserver.api.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class BoardCommand {

    @Getter
    @ToString
    @RequiredArgsConstructor
    public static class CreatePostCommand {
        private final String author;
        private final String title;
        private final String content;
    }
}

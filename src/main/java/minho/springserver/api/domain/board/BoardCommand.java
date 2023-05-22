package minho.springserver.api.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

// Command Create/Modify/Remove로 정의
public class BoardCommand {

    @Getter
    @RequiredArgsConstructor
    public static class CreatePostCommand {
        private final String author;
        private final String title;
        private final String content;
    }

    @ToString
    @Getter
    @RequiredArgsConstructor
    public static class ModifyCommand {
        private final Long postId;
        private final String author;
        private final String title;
        private final String content;
    }
}

package minho.springserver.api.application.board;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.board.BoardCommand;
import minho.springserver.api.domain.board.BoardInfo;
import minho.springserver.api.domain.board.BoardService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardApplication {
    private final BoardService boardService;

    public Long createPost(BoardCommand.CreatePostCommand command) {
        Long insertedId = this.boardService.createPost(command);
        return insertedId;
    }

    public List<BoardInfo.PostInfo> findPosts() {
        List<BoardInfo.PostInfo> posts = this.boardService.findPosts();
        return posts;
    }
}

package minho.springserver.api.application.board;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.board.BoardCommand;
import minho.springserver.api.domain.board.BoardService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardApplication {
    private final BoardService boardService;

    public Long createPost(BoardCommand.CreatePostCommand command) {
        Long insertedId = this.boardService.createPost(command);
        return insertedId;
    }
}

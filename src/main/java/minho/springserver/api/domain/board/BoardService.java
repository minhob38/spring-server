package minho.springserver.api.domain.board;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.infrastructure.board.BoardCreateImpl;
import minho.springserver.api.infrastructure.board.BoardReadImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardCreateImpl boardCreate;
    private final BoardReadImpl boardRead;

    public Long createPost(BoardCommand.CreatePostCommand command) {
        String author = command.getAuthor();
        String title = command.getTitle();
        String content = command.getContent();
        Long insertedId = this.boardCreate.createPost(author, title, content);
        return  insertedId;
    }
}

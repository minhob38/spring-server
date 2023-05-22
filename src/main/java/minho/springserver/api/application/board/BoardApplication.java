package minho.springserver.api.application.board;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.board.BoardCommand;
import minho.springserver.api.domain.board.BoardInfo;
import minho.springserver.api.domain.board.BoardQuery;
import minho.springserver.api.domain.board.BoardService;
import minho.springserver.exception.BoardException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardApplication {
    private final BoardService boardService;

    public Long createPost(BoardCommand.CreatePostCommand command) {
        Long createdId = this.boardService.createPost(command);
        return createdId;
    }

    public List<BoardInfo.PostInfo> findPosts() {
        List<BoardInfo.PostInfo> posts = this.boardService.readPosts();
        return posts;
    }

    public BoardInfo.PostInfo findPost(BoardQuery.ReadPostQuery query) throws BoardException {
        BoardInfo.PostInfo post = this.boardService.readPost(query);
        return post;
    }

    public Long modifyPost(BoardCommand.ModifyCommand command) throws BoardException {
        Long modifiedId = this.boardService.modifyPost(command);
        return modifiedId;
    }
}

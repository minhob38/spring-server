package minho.springserver.api.domain.board;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.infrastructure.board.BoardCreateImpl;
import minho.springserver.api.infrastructure.board.BoardReadImpl;
import minho.springserver.exception.BoardException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardCreateImpl boardCreate;
    private final BoardReadImpl boardRead;

    public Long createPost(BoardCommand.CreatePostCommand command) {
        String author = command.getAuthor();
        String title = command.getTitle();
        String content = command.getContent();
        Long createdId = this.boardCreate.insertPost(author, title, content);
        return  createdId;
    }

    public List<BoardInfo.PostInfo> readPosts() {
        List<BoardInfo.PostInfo> posts = this.boardRead.findPosts();
        return posts;
    }

    public BoardInfo.PostInfo readPost(BoardQuery.ReadPostQuery query) throws BoardException {
        Long postId = query.getPostId();
        Optional<BoardInfo.PostInfo> post = this.boardRead.findPost(postId);

        if (post.isEmpty()) {
            throw new BoardException("post does not exits :(");
        }

        return post.get();
    }

    public Long modifyPost(BoardCommand.ModifyCommand command) throws BoardException {
        Long postId = command.getPostId();
        String author = command.getAuthor();
        String title = command.getTitle();
        String content = command.getContent();

        Optional<BoardInfo.PostInfo> post = this.boardRead.findPost(postId);

        if (post.isEmpty()) {
            throw new BoardException("post does not exits :(");
        }

        Long modifiedId = this.boardCreate.updatePost(postId, author, title, content);
        return modifiedId;
    }
}

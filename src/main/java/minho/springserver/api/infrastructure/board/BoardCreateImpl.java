package minho.springserver.api.infrastructure.board;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.board.BoardCreate;
import org.springframework.stereotype.Component;

// CreateImpl -> insert/update/delete로 정의
@Component
@RequiredArgsConstructor
public class BoardCreateImpl implements BoardCreate {
    public final PostsRepository postsRepository;

    @Override
    public Long insertPost(String author, String title, String content) {
        Long insertedId = this.postsRepository.insert(author, title, content);
        return insertedId;
    }

    @Override
    public Long updatePost(Long postId, String author, String title, String content) {
        Long updatedId = this.postsRepository.update(postId, author, title, content);
        return updatedId;
    }

    @Override
    public Long deletePost(Long postId) {
        Long deletedId = this.postsRepository.delete(postId);
        return deletedId;
    }
}

package minho.springserver.api.infrastructure.board;

import lombok.RequiredArgsConstructor;
import minho.springserver.dao.PostsRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardCreateImpl {
    public final PostsRepository postsRepository;

    public Long createPost(String author, String title, String content) {
        Long insertedId = this.postsRepository.save(author, title, content);
        return insertedId;
    }
}

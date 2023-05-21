package minho.springserver.api.infrastructure.board;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.board.BoardInfo;
import minho.springserver.dao.PostsRepository;
import minho.springserver.entity.Posts;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BoardReadImpl {
    public final PostsRepository postsRepository;

    public List<BoardInfo.PostInfo> findPosts() {
        List<Posts> posts = this.postsRepository.findAll();

        return posts.stream().map(post -> {
            Long postId = post.getId();
            String author = post.getAuthor();
            String title = post.getTitle();
            String content = post.getContent();

            return new BoardInfo.PostInfo(postId, author, title, content);
        }).collect(Collectors.toList());
    }
}

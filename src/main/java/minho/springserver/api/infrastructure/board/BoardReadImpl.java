package minho.springserver.api.infrastructure.board;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.board.BoardInfo;
import minho.springserver.api.domain.board.entity.Posts;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// ReadImpl -> find로 정의
@Component
@RequiredArgsConstructor
public class BoardReadImpl implements minho.springserver.api.domain.board.BoardRead {
    public final PostsRepository postsRepository;

    @Override
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

    @Override
    public Optional<BoardInfo.PostInfo> findPost(Long postId) {
        Optional<Posts> post = this.postsRepository.findById(postId);

        if (post.isEmpty()) return Optional.empty();

        String author = post.get().getAuthor();
        String title = post.get().getTitle();
        String content = post.get().getContent();

        return Optional.of(new BoardInfo.PostInfo(postId, author, title, content));
    }
}

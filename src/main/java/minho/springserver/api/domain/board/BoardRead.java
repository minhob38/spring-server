package minho.springserver.api.domain.board;

import java.util.List;
import java.util.Optional;

public interface BoardRead {
    List<BoardInfo.PostInfo> findPosts();

    Optional<BoardInfo.PostInfo> findPost(Long postId);
}

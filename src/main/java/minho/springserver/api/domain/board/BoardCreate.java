package minho.springserver.api.domain.board;

public interface BoardCreate {
    Long insertPost(String author, String title, String content);

    Long updatePost(Long postId, String author, String title, String content);

    Long deletePost(Long postId);
}

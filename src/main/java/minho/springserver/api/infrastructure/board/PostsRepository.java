package minho.springserver.api.infrastructure.board;

import minho.springserver.api.domain.board.entity.Posts;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

// Repository -> find/insert/update/delete로 정의
@Repository
public class PostsRepository {
    private final EntityManager em;

    public PostsRepository(EntityManager em) {
        this.em = em;
    }

    public Long insert(String author, String title, String content) {
      Posts post = new Posts();
      post.setAuthor(author);
      post.setTitle(title);
      post.setContent(content);
      post.setCreatedAt(new Date()); // TODO: entity default 정의
      this.em.persist(post);
      return post.getId();
    }

    public List<Posts> findAll() {
        List<Posts> posts = this.em.createQuery("select p from Posts p", Posts.class).getResultList();
        return posts;
    }

    public Optional<Posts> findById(Long id) {
        Posts post = this.em.find(Posts.class, id);
        return Optional.ofNullable(post);
    }

    public Long update(Long postId, String author, String title, String content) {
        Posts post = this.em.find(Posts.class, postId);

        if (author != null) post.setAuthor(author);
        if (title != null) post.setTitle(title);
        if (content != null) post.setContent(content);

        return post.getId();
    }

    public Long delete(Long id) {
        Posts post = this.em.find(Posts.class, id);
        this.em.remove(post);
        return post.getId();
    }
}

package minho.springserver.api.infrastructure.board;

import minho.springserver.api.domain.board.entity.Posts;
import minho.springserver.dto.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
public class PostsRepository {
    private final EntityManager em;

    public PostsRepository(EntityManager em) {
        this.em = em;
    }

    public Long save(String author, String title, String content) {
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

    public Posts findById(Long id) {
        Posts post = this.em.find(Posts.class, id);
        return post;
    }

    public void update(Long id, Post update) {
        String content = update.getContent();
        Posts post = this.findById(id);
        post.setContent(content);
    }

    public void delete(Long id) {
        Posts post = this.findById(id);
        this.em.remove(post);
    }
}

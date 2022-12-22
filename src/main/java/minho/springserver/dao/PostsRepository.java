package minho.springserver.dao;

import minho.springserver.dto.Post;
import minho.springserver.entity.Posts;
import minho.springserver.entity.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
      em.persist(post);
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
//    public List<Users> findAll() {
//        List<Users> result = this.em.createQuery("select u from Users u", Users.class).getResultList();
//        return result;
//    }
//
//    public Optional<Users> findByEmail(String email) {
//        try {
//        Users user = this.em.createQuery("select u from Users u where u.email = :email", Users.class)
//                .setParameter("email", email)
//                .getSingleResult();
//        /* class toString method는 인스턴스의 해시주소를 hex형태로 반환합니다. */
//        System.out.println(Integer.toHexString(user.hashCode()));
//        System.out.println(user);
//        return Optional.of(user);
//        } catch (NoResultException e)  {
//            System.out.println("해당 email의 회원이 없습니다. : (");
//            return Optional.ofNullable(null);
//        }
//    }
//
//    public Long saveUser(String email, String hash) {
//        Users user = new Users();
//        user.setEmail(email);
//        user.setPassword(hash);
//        /* Date -> ZonedLocalDate로 바꾸기 */
//        user.setCreatedAt(new Date());
//        this.em.persist(user);
//        return user.getId();
//    }
//
//    public Users findById(Long id) {
//        Users result = this.em.find(Users.class, id);
//        return result;
//    }
}

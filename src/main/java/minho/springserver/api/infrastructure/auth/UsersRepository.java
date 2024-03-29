package minho.springserver.api.infrastructure.auth;

import minho.springserver.api.domain.auth.entity.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class UsersRepository {
    private final EntityManager em;
    public UsersRepository(EntityManager em) {
        this.em = em;
    }

    public List<Users> findAll() {
        List<Users> result = this.em.createQuery("select u from Users u", Users.class).getResultList();
        return result;
    }

    public Optional<Users> findById(Long id) {
        Users user = this.em.find(Users.class, id); // <- 조회하지 못하면 null을 반환합니다.
        return Optional.ofNullable(user);
    }

    public Optional<Users> findByEmail(String email) {
        try {
            Users user = this.em.createQuery("select u from Users u where u.email = :email", Users.class)
                .setParameter("email", email)
                .getSingleResult(); // <- 조회하지 못하면 NoResultException을 생성합니다.
            /* class toString method는 인스턴스의 해시주소를 hex형태로 반환합니다. */
            System.out.println(Integer.toHexString(user.hashCode()));
            return Optional.of(user);
        } catch (NoResultException e)  {
            System.out.println("해당 email의 회원이 없습니다. : (");
            return Optional.ofNullable(null);
        }
    }

    public Long create(String email, String hash) {
        Users user = new Users();
        user.setEmail(email);
        user.setPassword(hash);
        /* Date -> ZonedLocalDate로 바꾸기 */
        user.setCreatedAt(new Date());
        this.em.persist(user);
        return user.getId();
    }

    public Long updatePassword(Long id, String hash) {
        Users user = this.em.find(Users.class, id); // <- 조회하지 못하면 null을 반환합니다.
        user.setPassword(hash);
        user.setUpdatedAt(new Date());
//        this.em.persist(user);
        return user.getId();
    }

    public Long delete(Long id) {
        Users user = this.em.find(Users.class, id); // <- 조회하지 못하면 null을 반환합니다.
        this.em.remove(user);
        return user.getId();
    }
}

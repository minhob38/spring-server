package minho.springserver.dao;

import minho.springserver.entity.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public Optional<Users> findByEmail(String email) {
        try {
        Users user = this.em.createQuery("select u from Users u where u.email = :email", Users.class)
                .setParameter("email", email)
                .getSingleResult();
        /* class toString method는 인스턴스의 해시주소를 hex형태로 반환합니다. */
        System.out.println(Integer.toHexString(user.hashCode()));
        System.out.println(user);
        return Optional.of(user);
        } catch (NoResultException e)  {
            System.out.println("해당 email의 회원이 없습니다. : (");
            return Optional.ofNullable(null);
        }
    }

    public Users findById(Long id) {
        Users result = this.em.find(Users.class, id);
        return result;
    }
}

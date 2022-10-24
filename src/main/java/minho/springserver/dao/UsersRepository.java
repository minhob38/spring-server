package minho.springserver.dao;

import minho.springserver.entity.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    public Users findById() {
        Users result = this.em.find(Users.class,1L);
        return result;
    }
}

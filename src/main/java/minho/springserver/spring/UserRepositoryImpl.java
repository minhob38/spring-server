package minho.springserver.spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Component("userRepoBean") // @ComponentScan으로 등록되는 bean의 이름은 기본적으로 class 이름과 같으며, 별도로 설정할 수 있습니다.
public class UserRepositoryImpl implements UserRepository{
    private static Map<String, User> store = new HashMap<>();

    @Override
    public User findUserByEmail(String email) {
        User user = store.get(email);
        return user;
    }

    @Override
    public User removeUserByEmail(String email) {
        return null;
    }

    @Override
    public User createUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        store.put(email, user);
        return user;
    }

    @PostConstruct
    public void init() {
        User user = new User();
        user.setEmail("qwe@gmail.com");
        user.setPassword("asdfzxcv");
        store.put("qwe@gmail.com", user);
        System.out.println("=== init ===");
        System.out.println(store);
    }

    @PreDestroy
    public void close() {
        System.out.println("=== clean ===");
        System.out.println(store);
        store.clear();
        System.out.println(store);
    }
}

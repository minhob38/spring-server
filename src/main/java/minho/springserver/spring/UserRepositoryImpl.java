package minho.springserver.spring;

import java.util.HashMap;
import java.util.Map;

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
}

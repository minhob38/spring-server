package minho.springserver.spring;

public interface UserRepository {
    User findUserByEmail(String email);
    User removeUserByEmail(String email);
    User createUser(String email, String password);
}

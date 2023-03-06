package minho.springserver.domain.auth;

public interface AuthCreate {
    String createHash(String password);

    Long saveUser(String email, String password);
}
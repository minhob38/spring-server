package minho.springserver.api.domain.auth;

public interface AuthCreate {
    String createHash(String password);

    Long insertUser(String email, String password);

    Long updatePassword(Long userId, String hash);

    Long deleteUser(Long userId);
}

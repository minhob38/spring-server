package springstudy;

public interface AuthService {
    void signUp(String email, String password);
    User signIn(String email, String password);
    void signOut(String email, String password);
}

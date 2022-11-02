package springstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signUp(String email, String password) {
        userRepository.createUser(email, password);
    }

    @Override
    public User signIn(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        return user;
    }

    @Override
    public void signOut(String email, String password) {
        User user = userRepository.removeUserByEmail(email);
    }
}

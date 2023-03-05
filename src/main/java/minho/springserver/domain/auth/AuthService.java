package minho.springserver.domain.auth;

import lombok.RequiredArgsConstructor;
import minho.springserver.entity.Users;
import minho.springserver.exception.AuthException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRead authRead;

    public AuthInfo.SignupInfo signUp(String email, String password) {
        Optional<Users> user = this.authRead.findUserByEmail(email);

//        if (user.isPresent()) {
//            throw new AuthException("user already exists");
//        }

        String hash = this.authService.createHash(password);
        this.usersRepository.save(email, hash);
    }


}

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
    private final AuthCreate authCreate;

    public AuthInfo.SignupInfo signup(AuthCommand.SignupCommand command) throws AuthException {
        String email = command.getEmail();
        String password = command.getPassword();
        Optional<Users> user = this.authRead.findUserByEmail(email);

        if (user.isPresent()) {
            throw new AuthException("user already exists");
        }

        String hash = this.authCreate.createHash(password);
        Long userId = this.authCreate.saveUser(email, hash);

        return new AuthInfo.SignupInfo(userId);
    }

    public AuthInfo.SigninInfo signin(AuthCommand.SigninCommand command) throws AuthException {
        String email = command.getEmail();
        String password = command.getPassword();
        Optional<Users> user = this.authRead.findUserByEmail(email);

        if (!user.isPresent()) {
            throw new AuthException("user does not exists");
        }

        String hash = user.get().getPassword();
        boolean isMatchPassword = this.authRead.checkIsMatchPassword(password, hash);

        if (!isMatchPassword) {
            throw new AuthException("password is invalid");
        }

        Long userId = user.get().getId();

        return new AuthInfo.SigninInfo(userId);
    }
}

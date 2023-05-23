package minho.springserver.api.domain.auth;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.auth.entity.Users;
import minho.springserver.exception.AuthException;
import org.springframework.stereotype.Service;
import java.util.Optional;


// Service -> Create/Read/Modify/Remove로 정의
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
        Long userId = this.authCreate.insertUser(email, hash);

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

    public void modifyPassword(AuthCommand.ModifyPasswordCommand command) throws AuthException {
        Long userId = command.getUserId();
        String currentPassword = command.getCurrentPassword();
        String newPassword = command.getNewPassword();

        Optional<Users> user = this.authRead.findUserById(userId);

        if (newPassword.equals((currentPassword))) {
            throw new AuthException("new password should be different with current password");
        }

        if (!user.isPresent()) {
            throw new AuthException("user does not exists");
        }

        String currentHash = user.get().getPassword();
        boolean isMatchPassword = this.authRead.checkIsMatchPassword(currentPassword, currentHash);

        if (!isMatchPassword) {
            throw new AuthException("password is invalid");
        }

        String newHash = this.authCreate.createHash(newPassword);

        this.authCreate.updatePassword(userId, newHash);
    }

    public AuthInfo.UserInfo readMe(AuthQuery.ReadMeQuery query) throws AuthException {
        Long userId = query.getUserId();

        Optional<Users> user = this.authRead.findUserById(userId);

        if (!user.isPresent()) {
            throw new AuthException("user does not exists");
        }

        String userEmail = user.get().getEmail();

        return new AuthInfo.UserInfo(userId, userEmail);
    }

    public void signout(AuthCommand.SignoutCommand command) throws AuthException {
        Long userId = command.getUserId();

        Optional<Users> user = this.authRead.findUserById(userId);

        if (!user.isPresent()) {
            throw new AuthException("user does not exists");
        }

        this.authCreate.deleteUser(userId);
    }
}

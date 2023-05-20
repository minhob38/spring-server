package minho.springserver.api.application.auth;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.auth.AuthCommand;
import minho.springserver.api.domain.auth.AuthInfo;
import minho.springserver.api.domain.auth.AuthService;
import minho.springserver.exception.AuthException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthApplication {
    private final AuthService authService;
    public AuthInfo.SignupInfo signup(AuthCommand.SignupCommand command) throws AuthException {
        AuthInfo.SignupInfo signupInfo = this.authService.signup(command);
        return signupInfo;
    }

    public AuthInfo.SigninInfo signin(AuthCommand.SigninCommand command) throws AuthException {
        AuthInfo.SigninInfo signinInfo = this.authService.signin(command);
        return signinInfo;
    }

    public void updatePassword(AuthCommand.UpdatePasswordCommand command) throws AuthException {
        this.authService.updatePassword(command);
    }

    public AuthInfo.UserInfo findMe(AuthCommand.ReadMeCommand command) throws AuthException {
        AuthInfo.UserInfo userInfo =  this.authService.findMe(command);
        return userInfo;
    }

    public void signout(AuthCommand.SignoutCommand command) throws AuthException {
        this.authService.signout(command);
    }
}

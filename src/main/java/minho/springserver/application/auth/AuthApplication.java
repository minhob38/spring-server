package minho.springserver.application.auth;

import lombok.RequiredArgsConstructor;
import minho.springserver.domain.auth.AuthCommand;
import minho.springserver.domain.auth.AuthInfo;
import minho.springserver.domain.auth.AuthService;
import minho.springserver.exception.AuthException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthApplication {
    private final AuthService authService;
    public AuthInfo.SignupInfo signUp(AuthCommand.SignUpCommand command) throws AuthException {
        AuthInfo.SignupInfo info = this.authService.signUp(command);
        return info;
    }
}

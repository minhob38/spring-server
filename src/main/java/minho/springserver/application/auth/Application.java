package minho.springserver.application.auth;

import minho.springserver.domain.auth.AuthInfo;

public class Application {
    public AuthInfo.SignupInfo signUp(String email, String password) {
        // service
        return new AuthInfo.SignupInfo("token");
    }
}

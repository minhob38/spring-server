package minho.springserver.api.interfaces.auth;


import lombok.Getter;
import lombok.ToString;
import minho.springserver.api.domain.auth.AuthInfo;

// DTO -> Create/Read/Modify/Remove로 정의
public class AuthDto {
    // GET-api/auth/signup
    static class Signup {
        @ToString
        @Getter
        static class Data {
            private final Long userId;

            Data(AuthInfo.SignupInfo signupInfo) {
                this.userId = signupInfo.getUserId();
            }
        }
    }

    // GET-api/auth/me
    static class ReadMe {
        @ToString
        @Getter
        static class Data {
            private final Long userId;
            private final String email;

            Data(AuthInfo.UserInfo userInfo ) {
                this.userId = userInfo.getUserId();
                this.email = userInfo.getEmail();
            }
        }
    }
}

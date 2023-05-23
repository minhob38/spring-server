package minho.springserver.api.interfaces.auth;


import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import minho.springserver.api.domain.auth.AuthInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

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

    // Patch-api/auth/password
    static class ModifyPassword {
        @Getter
        static class Form {
            @NotBlank(message = "current password is required")
            @Length(max = 10)
             String currentPassword;

            @NotBlank
            @Length(max = 10)
             String newPassword;


            // ModelAttribute는 member 할당을 위해, setter 함수가 있어야합니다.
            public void setCurrentPassword(String currentPassword) {
                this.currentPassword = currentPassword;
            }

            public void setNewPassword(String newPassword) {
                this.newPassword = newPassword;
            }
        }

    }
}

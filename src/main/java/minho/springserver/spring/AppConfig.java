/*
[AppConfig]
* AppConfig
- application의 전체적인 설정을 합니다.
- class들의 관심사 분리를 합니다. (관심사 분리 service, repository 등)
- class들은 생성자 함수 인자를 interface로 만들고, AppConfig가 구현체를 만들어 class에 넘겨줍니다.
- 구현체를 만들고, 생성자 주입을 통해 DIP를 지킬 수 있습니다. (이를 의존관계주입(Dependency Injection)이라 합니다.)
- AppConfig처럼 의존관계를 연결해주는 class를 DI container(IOC container)라 합니다.

* Spring Bean
- @Configuration이 붙은 configuration class를 설정 정보로 사용합니다.
- @Configuration이 없어도 bean을 등록할 수 있지만, single tone으로 만들어지지 않습니다.
- @Bean이 붙은 method를 실행하여 반환된 instance(bean)를 spring container에 등록합니다.
*/

package minho.springserver.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean(name="userRepoBean") // bean이름은 기본적으로 method 이름과 같으며, name으로 설정할 수 있습니다.
    public UserRepository userRepository() {
        System.out.println("UserRepositoryImpl Dependency Injection");
        return new UserRepositoryImpl();
    }

    @Bean
    public AuthService authService() {
        System.out.println("AuthServiceImpl Dependency Injection");
        return new AuthServiceImpl(userRepository()); // 의존관계 주입
    }
}

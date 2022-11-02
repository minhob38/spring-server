/*
[spring container / bean]
* spring container
- spring container는 BeanFactory와 Application Context가 있습니다.
- ApplicationContext는 BeanFactory를 inherit 받고 application 관리를 위해 더 많은 기능들이 있습니다.
- 따라서, 일반적으로 ApplicationContext를 spring container로 간주합니다.

* bean
- bean은 getBean(bean 이름 및/또는 bean type)으로 조회합니다.
- bean type으로 조회할때 같은 type에 여러 bean이 있으면 error가 발생합니다. (이때 bean 이름으로 조회하면 해결할 수 있습니다.)
- getBeansOfType(bean type)으로 같은 type의 모든 bean을 조회할 수 있습니다.
 */

package springstudy;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AuthApp {
    public static void main(String[] args) {
        String mode = "w bean"; // w/o config & w config & w bean & w auto bean

        if (mode == "w/o config") {
            System.out.println("=== w/o config ===");
            UserRepository userRepository = new UserRepositoryImpl();
            AuthService authService = new AuthServiceImpl(userRepository); // 의존관계 주입
            authService.signUp("abc@gmail.com", "qwerasdf");
            User user = authService.signIn("abc@gmail.com", "qwerasdf");
            System.out.println("email:" + user.getEmail() + " / " + "password:" + user.getPassword());
            authService.signOut("abc@gmail.com", "qwerasdf");
        }

        if (mode == "w config") {
            System.out.println("=== w config ===");
            AppConfig appConfig = new AppConfig();
            AuthService authService =  appConfig.authService();
            authService.signUp("abc@gmail.com", "qwerasdf");
            User user = authService.signIn("abc@gmail.com", "qwerasdf");
            System.out.println("email:" + user.getEmail() + " / " + "password:" + user.getPassword());
            authService.signOut("abc@gmail.com", "qwerasdf");
        }

        if (mode == "w bean") {
            System.out.println("=== w bean ===");
            ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
            AuthService authService = applicationContext.getBean("authService", AuthService.class);
            authService.signUp("abc@gmail.com", "qwerasdf");
            User user = authService.signIn("abc@gmail.com", "qwerasdf");
            System.out.println("email:" + user.getEmail() + " / " + "password:" + user.getPassword());
            authService.signOut("abc@gmail.com", "qwerasdf");

            System.out.println("=== bean 조회 ===");
            System.out.println("=== 전체 bean 조회 ===");
            String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                Object bean = applicationContext.getBean(beanDefinitionName);
                System.out.println("name=" + beanDefinitionName + " object=" + bean);
            }
        }

        if (mode == "w auto bean") {
            System.out.println("=== w auto bean ===");
            ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);
            AuthService authService = applicationContext.getBean("authServiceImpl", AuthService.class);
            authService.signUp("abc@gmail.com", "qwerasdf");
            User user = authService.signIn("abc@gmail.com", "qwerasdf");
            System.out.println("email:" + user.getEmail() + " / " + "password:" + user.getPassword());
            authService.signOut("abc@gmail.com", "qwerasdf");

            System.out.println("=== bean 조회 ===");
            System.out.println("=== 전체 bean 조회 ===");
            String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                Object bean = applicationContext.getBean(beanDefinitionName);
                System.out.println("name=" + beanDefinitionName + " object=" + bean);
            }
        }
    }
}

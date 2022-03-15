/*
[spring / bean lifecylce]
* spring container / bean의 lifecycle은 아래와 같습니다.
1) spring container 생성
2) bean 생성
3) dependency injection
4) initialize callback
5) destroy callback
5) spring 종료

* life cycle에 따라 method를 실행할 수 있습니다.
- @PostConstruct / @PreDestroy
- @Bean(initMethod = "init", destroyMethod = "close")
- InitializingBean /  DisposableBean
 */

package minho.springserver.spring;

public interface UserRepository {
    User findUserByEmail(String email);
    User removeUserByEmail(String email);
    User createUser(String email, String password);
}

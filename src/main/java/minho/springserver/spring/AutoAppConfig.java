/*
[AutoAppConfig]
* ComponentScan
- @Component가 붙은 class의 instance를 bean으로 등록합니다.
- @AutoWired가 붙은 생성자에 DI를 해줍니다.
*/

package minho.springserver.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {
}

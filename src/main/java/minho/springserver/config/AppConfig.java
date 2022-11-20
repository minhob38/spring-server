package minho.springserver.config;

import minho.springserver.filter.AuthCheckFilter;
import minho.springserver.interceptor.LogInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).order(1).addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean authCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean =  new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AuthCheckFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}

package minho.springserver.config;

import minho.springserver.filter.AuthCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean authCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean =  new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AuthCheckFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}

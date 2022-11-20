package minho.springserver.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        System.out.println("interceptor:preHandle" + " / " + "endpoint:" + requestUri);
        long startedAt = System.currentTimeMillis();
        request.setAttribute("startedAt", startedAt);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String requestUri = request.getRequestURI();
        System.out.println("interceptor:postHandle" + " / " + "endpoint:" + requestUri);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestUri = request.getRequestURI();
        long finishedAt = System.currentTimeMillis();
        long startedAt = (long)request.getAttribute("startedAt");
        long responseTime = finishedAt - startedAt;
        System.out.println("interceptor:afterCompletion" + " / " + "endpoint:" + requestUri + " / " + "response time(ms):" + responseTime);
    }
}

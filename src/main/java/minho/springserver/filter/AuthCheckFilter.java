package minho.springserver.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import minho.springserver.dto.ErrorResponse;
import org.springframework.http.*;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthCheckFilter implements Filter {
    private static final String[] nochecks = {"/api/auth/signup", "/api/auth/signin"};
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private boolean checkIsAuthCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(nochecks, requestURI);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        System.out.println("do filter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        String requestURI = httpRequest.getRequestURI();

        try {
            if (!this.checkIsAuthCheckPath(requestURI)) {
                chain.doFilter(request, response);
                return;
            }

            if (session == null || session.getAttribute("auth-key") == null) {
                System.out.println("session does not exist");
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage("session does not exist");
                new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
                String errorResponseJson = objectMapper.writeValueAsString(errorResponse);
                // TODO: status code, header 설정 (HttpServletResponse로)
                response.setContentType("application/json");
                response.getWriter().write(errorResponseJson);
                return;
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package minho.springserver.filter;

import minho.springserver.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthCheck implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        System.out.println("do filter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("auth-key") == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("session does not exist");
            new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
            return;
        }

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

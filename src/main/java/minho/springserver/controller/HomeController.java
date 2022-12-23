package minho.springserver.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "homeController", urlPatterns = "/api/home")
public class HomeController extends HttpServlet {
    @Override
    //    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String method = req.getMethod();

        switch (method) {
            case "GET":
                res.getWriter().write("get - api server : )");
                break;
            case "POST":
                res.getWriter().write("post - api server : )");
                break;
            default:
                res.getWriter().write("api server : )");
        }
    }
}

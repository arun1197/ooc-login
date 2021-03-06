package io.muic.ooc.webapp.servlet;

/*source: http://javaandj2eetutor.blogspot.sg/2014/01/login-application-using-jsp-servlet-and.html */

import io.muic.ooc.webapp.Main;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(
        name = "LoginServlet",
        urlPatterns = {"/login"}
)
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // do login post logic
        // extract username and password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(request.getSession().getAttribute("username")!=null){
            response.sendRedirect("/users");
        }
        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
            try {
                if (Main.mySQLJava.checkLogin(username, password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username",username);
                    response.sendRedirect("/users");

                } else {
                    String error = "username or password is in wrong format.";
                    request.setAttribute("error",error);
                    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
//                    response.sendRedirect("index.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            String error = "username or password is in wrong format.";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
        // check username and password against database
        // if valid then set username attribute to session via securityService
        // else put error message to render error on the login form
    }
}
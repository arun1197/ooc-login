package io.muic.ooc.webapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Don on 2/13/2017 AD.
 */

@WebServlet(
        name = "ErrorServlet",
        urlPatterns = {"/Error"}
)

public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(req.getSession().getAttribute("username")!=null){
            resp.sendRedirect("userList.jsp");
        }
        else{
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");

        try {
            if (req.getSession().getAttribute("username")!=null){
                resp.sendRedirect("userList.jsp");
            }
            else{
                resp.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

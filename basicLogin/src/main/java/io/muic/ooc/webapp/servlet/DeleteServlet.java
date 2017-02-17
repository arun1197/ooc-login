package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Main;

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
        name = "DeleteServlet",
        urlPatterns = {"/delete"}
)

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("delete.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        try {
            if(!req.getSession().getAttribute("username").equals(username)){
                Main.mySQLJava.DeleteRow(username);
                resp.sendRedirect("/users");
            } else {
                resp.sendRedirect("/users");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

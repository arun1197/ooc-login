package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Main;
import io.muic.ooc.webapp.ReadSQL;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
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
        name = "EditServlet",
        urlPatterns = {"/edit"}
)

public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("edit.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ReadSQL sql = new ReadSQL();
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        try {
            if(!StringUtils.isBlank(username) && !StringUtils.isBlank(firstname) && !StringUtils.isBlank(lastname)
                    && sql.notexistingUser(username)){
            Main.mySQLJava.UpdateRow(username,username,firstname,lastname);
            resp.sendRedirect("/users");
            }
            else{
                String error = "You cannot change to that username.";
                req.setAttribute("error",error);
                RequestDispatcher rd = req.getRequestDispatcher("/edit.jsp");
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

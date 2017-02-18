package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Main;
import io.muic.ooc.webapp.ReadSQL;

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
        name = "AddServlet",
        urlPatterns = {"/add"}
)

public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("add.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ReadSQL readSQL = new ReadSQL();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstname =  req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        try {
            if(readSQL.notexistingUser(username)==true){
                Main.mySQLJava.AddRow(username,password,firstname,lastname);
                resp.sendRedirect("/users");
            }else{
//                String error = "User exist. Enter a unique username.";
//                req.setAttribute("error", error);
//                RequestDispatcher rd = req.getRequestDispatcher("add.jsp");
//                rd.forward(req, resp);
                resp.sendRedirect("/add");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

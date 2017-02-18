package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Main;
import io.muic.ooc.webapp.ReadSQL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        //PrintWriter printWriter =  resp.getWriter();
        try {
//            System.out.println("Im here");
            for(List<String> i: readSQL.getUsers()){
                if (!i.contains(username)){
                    Main.mySQLJava.AddRow(username,password,firstname,lastname);
                    resp.sendRedirect("/users");
                }
                else{
                    resp.sendRedirect("/users");
                }
            }
//            System.out.println("added to db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package io.muic.ooc.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Don on 2/17/2017 AD.
 */
public class ReadSQL {
    private Connection connection;
    private Statement statement;
    public ResultSet resultSet;

    public List getUserFromTable() throws Exception{
        List<String> content_user = new ArrayList<>();
        try {
            //Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","1234");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM test.Users;");
            while(resultSet.next()){
                content_user.add(resultSet.getString("username"));
            }
        } finally {
            return content_user;
            }
    }

    public List getFnameFromTable() throws Exception{
        List<String> content_fname = new ArrayList<>();
        try {
            //Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","1234");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM test.Users;");
            while(resultSet.next()){
                content_fname.add(resultSet.getString("firstname"));
            }
        } finally {
            return content_fname;
        }
    }
    public List getLnameFromTable() throws Exception{
        List<String> content_lname = new ArrayList<>();
        try {
            //Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","1234");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM test.Users;");
            while(resultSet.next()){
                content_lname.add(resultSet.getString("lastname"));
            }
        } finally {
            return content_lname;
        }
    }

    public ArrayList<List<String>> getUsers() throws Exception{
        ArrayList<List<String>> lst = new ArrayList<>();
        try {
            //Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","1234");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM test.Users;");
            while(resultSet.next()){
                List<String> temp = new ArrayList<>();
                temp.add(resultSet.getString("username"));
                temp.add(resultSet.getString("firstname"));
                temp.add(resultSet.getString("lastname"));
                lst.add(temp);
            }
        } finally {
            close();
            return lst;
        }
    }
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
        }
    }
}

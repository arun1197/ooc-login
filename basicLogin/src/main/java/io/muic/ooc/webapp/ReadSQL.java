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


    public ArrayList<List<String>> getUsers() throws Exception{
        ArrayList<List<String>> lst = new ArrayList<>();
        try {
            //Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","password");
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
    public boolean existingUser(String username) throws Exception{
        boolean user_exist = true;
        for(List<String> i : getUsers()){
            if(!i.contains(username)){
                user_exist = false;

            }
        }
        return user_exist;
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

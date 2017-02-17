package io.muic.ooc.webapp;

import org.apache.commons.lang.StringUtils;

import java.sql.*;

public class MySQLJava {

    enum TestTableColumns {
        username, password;
    }

    private final String jdbcDriverStr;
    private final String jdbcURL;

    private Connection connection;
    private Statement statement;
    public static ResultSet resultSet;
    private ResultSet resultSet2;
//    private PreparedStatement preparedStatement;

    public MySQLJava(String jdbcDriverStr, String jdbcURL) {
        this.jdbcDriverStr = jdbcDriverStr;
        this.jdbcURL = jdbcURL;
    }

    public boolean checkLogin(String username, String password) throws Exception {
        boolean not_pass = false;
        try {
            //Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","1234");
            statement = connection.createStatement();
            resultSet2 = statement.executeQuery("SELECT * FROM test.Users;");
            while(resultSet2.next()){
                if(StringUtils.equals(resultSet2.getString("username"),username) &&
                        StringUtils.equals(resultSet2.getString("password"),password)) {
                        not_pass = true;
                }
        }
        } finally {
            close();
            return not_pass;
        }
    }

    public  void getResultSet(ResultSet resultSet) throws Exception {
        while (resultSet.next()) {
            String username = resultSet.getString(TestTableColumns.username.toString());
            String password = resultSet.getString(TestTableColumns.password.toString());
            System.out.println("username: " + username);
            System.out.println("password: " + password);
        }
    }

    public void DeleteRow(String name) {
        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","1234");
            PreparedStatement st = connection.prepareStatement("DELETE FROM test.Users WHERE username = ?");
            st.setString(1,name);
            st.executeUpdate();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void UpdateRow(String usernamedb, String username, String fname, String lname) {
        try {
//            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","1234");
            String query = "UPDATE test.Users SET username = ?, firstname = ?, lastname = ? WHERE username = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, fname);
            preparedStmt.setString(3, lname);
            preparedStmt.setString(4, usernamedb);
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddRow(String username, String password, String fname, String lname) {
        try {
            // create a mysql database connection
//            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","1234");
            // note that i'm leaving "date_created" out of this insert statement
            String query = "INSERT INTO test.Users (username, password, firstname, lastname) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,fname);
            preparedStatement.setString(4,lname);
            preparedStatement.executeUpdate();
            connection.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
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

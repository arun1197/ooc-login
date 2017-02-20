package io.muic.ooc.webapp;

import org.apache.commons.lang.StringUtils;

import java.sql.*;

public class MySQLJava {



    enum TestTableColumns {
        username, password;
    }

    private final String jdbcDriverStr;
    private final String jdbcURL;

    private static Connection connection;
    private static Statement statement;
    public static ResultSet resultSet2;


    public MySQLJava(String jdbcDriverStr, String jdbcURL) {
        this.jdbcDriverStr = jdbcDriverStr;
        this.jdbcURL = jdbcURL;
    }

    public static  boolean checkLogin(String username, String password) throws Exception {
        boolean not_pass = false;
        HashSalt hashSalt = new HashSalt();
        try {
            //Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","password");
            statement = connection.createStatement();
            resultSet2 = statement.executeQuery("SELECT * FROM test.Users;");
            while(resultSet2.next()){
                if(StringUtils.equals(resultSet2.getString("username"),username) &&
                            hashSalt.checkPassword(password,resultSet2.getString("password"))){
                        not_pass = true;
                }
        }
        } finally {
            close();
            return not_pass;
        }
    }

    public void DeleteRow(String name) {
        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","password");
            PreparedStatement st = connection.prepareStatement("DELETE FROM test.Users WHERE username = ?");
            st.setString(1,name);
            st.executeUpdate();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void UpdateRow( String id,String username, String fname, String lname) {
        try {
//            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","password");
            String query = "UPDATE test.Users SET username = ?, firstname = ?, lastname = ? WHERE username = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, fname);
            preparedStmt.setString(3, lname);
            preparedStmt.setString(4, id);

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
            HashSalt hashSalt = new HashSalt();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","password");
            // note that i'm leaving "date_created" out of this insert statement
            String query = "INSERT INTO test.Users (username, password, firstname, lastname) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2,hashSalt.hashPassword(password));
            preparedStatement.setString(3,fname);
            preparedStatement.setString(4,lname);
            preparedStatement.executeUpdate();
            connection.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }

    private static void close() {
        try {
            if (resultSet2 != null) {
                resultSet2.close();
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

package app.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnect {

    private static Connection conn;
    private static boolean status;

    public static Connection ConnectJDBC(){

        String username = "root";
        String password = "password07";
        String url = "jdbc:mysql://127.0.0.1:3306/?user=root";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }

        try{
            conn  = DriverManager.getConnection(url,username,password);
            status = true;
            return conn;

        }catch (SQLException e){
          e.printStackTrace();
          status = false;
          return null;
        }

    }

    public static boolean getConnectionStatus(){
        return status;
    }
}

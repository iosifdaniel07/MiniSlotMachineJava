package app.classes;

import java.sql.*;

public class Admin {

    private static Admin _instance = new Admin();
    private String _username;
    private String _password;

    private Admin(){

        try{
            Connection conn = MySQLConnect.ConnectJDBC();

            Statement stmt = conn.createStatement();

            ResultSet rs_Admin = stmt.executeQuery("SELECT `adminName` FROM `database`.`adminmehbet` WHERE `key` = 0");
            while(rs_Admin.next()){
                _username = rs_Admin.getString("adminName");
            }

            ResultSet rs_Pass = stmt.executeQuery("SELECT `adminPasword` FROM `database`.`adminmehbet` WHERE `key` = 0");
            while(rs_Pass.next()){

                _password = rs_Pass.getString("adminPasword");
            }

            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public static Admin getInstance() {
        return _instance;
    }

    public void changePassword(String newPassword){
        try{
            Connection conn = MySQLConnect.ConnectJDBC();

            String query = "UPDATE `database`.`adminmehbet` set `adminPasword` = ? WHERE `key` = 0";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,newPassword);

            preparedStatement.executeUpdate();

            _password = newPassword;

            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}

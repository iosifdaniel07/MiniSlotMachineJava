package app.gui;

import app.classes.MySQLConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminLoginForm {

    private JButton  _buttonLoginAdmin;
    private JPanel _loginPanelAdmin;
    private JLabel _adminLabel, _passAdminLabel;
    private JTextField _getAdminname;
    private JPasswordField _getPasswordAdmin;
    private JFrame _frame;
    private JLabel _labelImage;
    private ImageIcon _img;

    public AdminLoginForm(){

        _loginPanelAdmin = new JPanel();
        _loginPanelAdmin.setLayout(null);
        _loginPanelAdmin.setBackground(Color.WHITE);


        try{
            _img = new ImageIcon(this.getClass().getResource("images/adminMeh.JPG"));
        }catch (Exception e){
            e.printStackTrace();
        }
        _labelImage = new JLabel(_img);
        _labelImage.setBounds(420,30,550,220);
        _loginPanelAdmin.add( _labelImage);


        _adminLabel = new JLabel("Admin Username");
        _adminLabel.setBounds(600,300,150,20);
        _adminLabel.setForeground(Color.RED);
        _loginPanelAdmin.add(_adminLabel);

        _getAdminname = new JTextField(15);
        _getAdminname.setBounds(600,319,200,30);
        _loginPanelAdmin.add(_getAdminname);

        _passAdminLabel = new JLabel("Password");
        _passAdminLabel.setBounds(600,347,70,20);
        _passAdminLabel.setForeground(Color.RED);
        _loginPanelAdmin.add( _passAdminLabel);

        _getPasswordAdmin = new JPasswordField(15);
        _getPasswordAdmin.setBounds(600,367,200,30);
        _loginPanelAdmin.add( _getPasswordAdmin);

        _buttonLoginAdmin = new JButton("Login");
        _buttonLoginAdmin.setBounds(600,402,90,25);
        _buttonLoginAdmin.setForeground(Color.WHITE);
        _buttonLoginAdmin.setBackground(Color.RED);
        _buttonLoginAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String admin = _getAdminname.getText();
                String pass = _getPasswordAdmin.getText();
                String adminDataBase = null, passwordDataBase = null;


                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch(ClassNotFoundException ex){
                    ex.printStackTrace();
                }

                try{
                    Connection conn = MySQLConnect.ConnectJDBC();

                    Statement stmt = conn.createStatement();

                    ResultSet rs_Admin = stmt.executeQuery("SELECT `adminName` FROM `database`.`adminmehbet` WHERE `key` = 0");
                    while(rs_Admin.next()){

                        adminDataBase = rs_Admin.getString("adminName");
                    }

                    ResultSet rs_Pass = stmt.executeQuery("SELECT `adminPasword` FROM `database`.`adminmehbet` WHERE `key` = 0");
                    while(rs_Pass.next()){

                        passwordDataBase = rs_Pass.getString("adminPasword");
                    }

                    conn.close();

                }catch(SQLException ex){
                    ex.printStackTrace();
                }

                if(admin.equals(adminDataBase) && pass.equals(passwordDataBase)){
                    AdminGui a = new AdminGui();
                    _frame.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Username or password incorrect");
                }

            }
        });
        _loginPanelAdmin.add(_buttonLoginAdmin);


        _frame = new JFrame("Admin");
        _frame.setLocation( 200,10);
        _frame.setContentPane(_loginPanelAdmin);
        _frame.setSize(new Dimension(1200,800));
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setVisible(true);
    }

    public static void main(String[] args) {

        AdminLoginForm form = new AdminLoginForm();
    }

}

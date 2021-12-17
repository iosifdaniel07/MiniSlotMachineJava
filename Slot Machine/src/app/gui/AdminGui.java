package app.gui;

import app.classes.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class AdminGui {

    private JPanel _mainPanel;
    private JFrame _frame;
    private JButton _changePassword;
    private JPasswordField _passwordField;
    private JLabel _passwordLabel;

    public AdminGui(){

        if(SwingUtilities.isEventDispatchThread()){

          intialize();
        }else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {

                 intialize();
                }
            });
        }

    }

    private void intialize(){
        _frame = new JFrame("Admin");
        _mainPanel = new JPanel();
        _mainPanel.setBackground(Color.gray);
        _mainPanel.setLayout(null);


        _passwordLabel = new JLabel("New Password");
        _passwordLabel.setBounds(10,10,100,20);
        _passwordLabel.setBackground(Color.RED);
        _passwordLabel.setForeground(Color.WHITE);
        _mainPanel.add(_passwordLabel);

        _passwordField = new JPasswordField();
        _passwordField.setBounds(100,10,100,20);
        _mainPanel.add( _passwordField);

        _changePassword = new JButton("Change Password");
        _changePassword.setBounds(80,40,150,20);
        _changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String p1 = _passwordField.getText();
                Admin a = Admin.getInstance();
                a.changePassword(p1);
                JOptionPane.showMessageDialog(null,"Parola schimbata cu succes");
            }
        });
        _mainPanel.add(_changePassword);



        _frame.setContentPane(_mainPanel);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setLocation(500,90);
        _frame.setResizable(false);
        _frame.setSize(400,200);
        _frame.setVisible(true);
    }

    public static void main(String[] args) {

        try{
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    AdminGui a = new AdminGui();
                }
            });
        }catch(InterruptedException e){
            System.out.println("... s-a intrerupt executia!");
        }
        catch (InvocationTargetException ex){
            System.out.println("... eroare in metoda run() ");
        }

    }


}


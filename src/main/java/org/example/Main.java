package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Main extends JFrame{

    private JPanel panel1;

    private JPanel contentPane;

    private JTextField txtUsername;

    private JPasswordField txtPassword;

    private JLabel name;

    private JButton btnLogin;
    private JButton btnSignin;

    private ArrayList<SigninConstructor> list = new ArrayList<>();
    private ArrayList<LoginConstructor> list1 = new ArrayList<>();


    public Main(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        name = new JLabel("Đăng nhập");
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Tahoma",Font.BOLD,40));
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setBounds(80,0,300,100);
        contentPane.add(name);

        txtUsername = new JTextField();
        txtUsername.setBounds(50,100,400, 50);
        contentPane.add(txtUsername);

        txtPassword = new JPasswordField();
        txtPassword.setEchoChar('*');
        txtPassword.setBounds(50,170,400, 50);
        contentPane.add(txtPassword);

        btnLogin = new JButton("Đăng nhập");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LoginConstructor lc = new LoginConstructor();
                ConnectDatabase cd = new ConnectDatabase();
                String username = txtUsername.getText();
                String password = txtPassword.getText();

                String hashedUsername = hashUsername(username);
                String hashedPassword = hashPassword(password);

                lc.setUsername(hashedUsername);
                lc.setPassword(hashedPassword);

                boolean checkLogin = true;

                if (cd.addLogin(lc)) {
                    for (SigninConstructor sc : list) {
                        if (sc.getUsernameSignin().equals(username) && sc.getPasswordSignin().equals(password)) {
                            checkLogin = false;
                            list1.add(lc);
                        }
                    }
                }
                if(checkLogin)
                {
                    JOptionPane.showMessageDialog(null,"Đăng nhập thành công");
                }else{
                    JOptionPane.showMessageDialog(null,"Sai tên đăng nhập hoặc mật khẩu");
                }
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnLogin.setBounds(70, 250, 150, 50);
        contentPane.add(btnLogin);

        btnSignin = new JButton("Đăng ký");
        btnSignin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sign s = new Sign();
                s.setVisible(true);
                dispose();
            }
        });
        btnSignin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnSignin.setBounds(250, 250, 150, 50);
        contentPane.add(btnSignin);

    }

    public static void main(String[] args) {
        try {
            Main frame = new Main();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String hashPassword(String password) {
        try {
            // Tạo một instance của MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Mã hóa mật khẩu
            md.update(password.getBytes());
            byte[] byteData = md.digest();

            // Chuyển đổi byte array thành chuỗi hex
            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    private String hashUsername(String username) {
        try {
            // Tạo một instance của MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Mã hóa mật khẩu
            md.update(username.getBytes());
            byte[] byteData = md.digest();

            // Chuyển đổi byte array thành chuỗi hex
            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
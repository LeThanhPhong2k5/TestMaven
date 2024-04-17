package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Sign extends JFrame {

    private JPanel contentPane;

    private JTextField txtUsernameSignin;

    private JPasswordField txtPasswordSignin;

    private JPasswordField txtRepairPasswordSignin;

    private JLabel nameSignin;

    private JButton btnSignin;

    private ArrayList<SigninConstructor> list = new ArrayList<>();

    public Sign()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        nameSignin = new JLabel("Đăng nhập");
        nameSignin.setForeground(Color.BLACK);
        nameSignin.setFont(new Font("Tahoma",Font.BOLD,40));
        nameSignin.setHorizontalAlignment(SwingConstants.CENTER);
        nameSignin.setBounds(80,0,300,100);
        contentPane.add(nameSignin);

        txtUsernameSignin = new JTextField();
        txtUsernameSignin.setBounds(50,100,400, 50);
        contentPane.add(txtUsernameSignin);

        txtPasswordSignin = new JPasswordField();
        txtPasswordSignin.setEchoChar('*');
        txtPasswordSignin.setBounds(50,170,400, 50);
        contentPane.add(txtPasswordSignin);

        txtRepairPasswordSignin = new JPasswordField();
        txtRepairPasswordSignin.setEchoChar('*');
        txtRepairPasswordSignin.setBounds(50,240,400, 50);
        contentPane.add(txtRepairPasswordSignin);

        btnSignin = new JButton("Đăng ký");
        btnSignin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SigninConstructor sc = new SigninConstructor();
                ConnectDatabase cd = new ConnectDatabase();
                sc.setUsernameSignin(txtUsernameSignin.getText());
                sc.setPasswordSignin(txtPasswordSignin.getText());
                sc.setRepairPassword(txtRepairPasswordSignin.getText());

                if(sc.getPasswordSignin().equals(sc.getRepairPassword())) {
                    if(cd.addSignin(sc)){
                        JOptionPane.showMessageDialog(null, "Đăng ký thành công");
                        list.add(sc);
                        Main m = new Main();
                        m.setVisible(true);
                        dispose();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Mật khẩu nhập vào phải trùng với nhập lại mật khẩu");
                }
            }
        });
        btnSignin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnSignin.setBounds(150, 300, 150, 50);
        contentPane.add(btnSignin);

    }

    public static void main(String[] args)
    {
        try{
            Sign s = new Sign();
            s.setVisible(true);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}

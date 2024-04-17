package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectDatabase {
    private Connection con;
    public ConnectDatabase()
    {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "222222";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
            System.out.println("Kết nối cơ sở dữ liệu thành công " + url);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean addLogin(LoginConstructor lc){
        String sql = "INSERT INTO login (Username, Password) VALUES (?, ?)";
        try(PreparedStatement ps = con.prepareStatement(sql))
        {
             ps.setString(1, lc.getUsername());
             ps.setString(2, lc.getPassword());

             return ps.executeUpdate() > 0;

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public boolean addSignin(SigninConstructor sc){
        String sql = "INSERT INTO signin (Username, Password,RepairPassword) VALUES (?, ?, ?)";
        try(PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, sc.getUsernameSignin());
            ps.setString(2, sc.getPasswordSignin());
            ps.setString(3, sc.getRepairPassword());

            return ps.executeUpdate() > 0;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

}

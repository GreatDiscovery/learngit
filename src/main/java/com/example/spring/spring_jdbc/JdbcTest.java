package com.example.spring.spring_jdbc;

import java.sql.*;

/**
 * @author gavin
 * @date 2018/12/7 14:12
 */
public class JdbcTest {

    public static void main(String[] args) {
        // 原生jdbc操作
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/goods", "root", "123456");
            PreparedStatement pstm = con.prepareStatement("select * from t_admin");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("adminId"));
                System.out.println(rs.getString("adminname"));
                System.out.println(rs.getString("adminpwd"));
            }
            rs.close();
            pstm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

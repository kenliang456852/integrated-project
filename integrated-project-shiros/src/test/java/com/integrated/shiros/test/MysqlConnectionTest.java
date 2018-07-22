package com.integrated.shiros.test;

import java.sql.*;

/**
 * ClassName: mysqlconnectionTest
 * Description:
 * Author: liangchao
 * Date: 2018/7/21 1:08
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class MysqlConnectionTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1/integrated?useSSL=FALSE&serverTimezone=UTC","root", "root");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from busi_acct_info");
        while (rs.next()) {
            for (int i = 1; i <= 3; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
    }

}

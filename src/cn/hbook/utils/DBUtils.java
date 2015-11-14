package cn.hbook.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBUtils {
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/hbook";
        Connection conn = DriverManager.getConnection(url, "root", "1");
        return conn;
    }
    public static void close(PreparedStatement ps){
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void close(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
     
    public static void main(String[] args) throws SQLException {
         
       /* String answer = "";
        try {
            Connection conn = DBUtils.getConnection();
            StringBuilder sql = new StringBuilder("insert into t_user_book values(?, ?, ?)");
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, 1);
            ps.setInt(2, 85);
            ps.setInt(3, 4);
            int rs = ps.executeUpdate();
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("answer = " + answer);*/
    	
    	 Connection conn = DBUtils.getConnection();
         StringBuilder sql = new StringBuilder("select value from t_user_book where userid = ? and bookid = ?");
         PreparedStatement ps = conn.prepareStatement(sql.toString());
         ps.setInt(1, 1);
         ps.setInt(2, 85);
         ResultSet rs = ps.executeQuery();
         Integer value = 0;
         while (rs.next()) {
        	 value = rs.getInt("value");
         }
         System.out.println(value);
    }
}   
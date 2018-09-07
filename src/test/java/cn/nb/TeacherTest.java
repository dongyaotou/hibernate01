package cn.nb;

import cn.nb.bean.Teacher;

import java.sql.*;

public class TeacherTest {
    public static void main(String[] args) {
        //定义连接数据库的四要素
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mybatis";
        String userName = "root";
        String password="";
        //创建jdbc的api
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            //01.加载驱动
            Class.forName(driver);
           connection = DriverManager.getConnection(url, userName, password);
           //02.书写sql语句
            String sql="select * from teacher where id=?";
            ps=connection.prepareStatement(sql);
            //03.给参数赋值
            ps.setInt(1,2);
            rs=ps.executeQuery();
            //04.遍历结果即
            while(rs.next()){
                int id = rs.getInt("id");
                String name=rs.getString("name");
                int tid = rs.getInt("tid");
                Teacher t  =new Teacher(id,name,tid);
                System.out.println(t);



            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


}

package cn.nb;

import cn.nb.bean.Teacher;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
//这是使用hibernate的方式
public class TeacherORM {
    public static void main(String[] args) {
        //连接数据库的四要素
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/mybatis";
        String userName="root";
        String password="";
        //创建jdbc需要的api
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        //创建需要的好似提类对象，用于动态的赋值
        Object object=null;
        try {
            object = Class.forName("cn.nb.bean.Teacher").newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        try {
            //01.加载驱动
            Class.forName(driver);
            connection= DriverManager.getConnection(url,userName,password);
           //02.书写sql语句
            String sql="select * from teacher where id=?";
            ps = connection.prepareStatement(sql);
            //03.给参数赋值
            ps.setInt(1,2);
            rs=ps.executeQuery();
            //04.遍历结果即
            while (rs.next()){
                ResultSetMetaData data=rs.getMetaData();
                int count = data.getColumnCount();
                System.out.println("结果集中的列数为："+count);
                for(int i=1;i<=count;i++){
                    String name = data.getColumnName(i);
                    System.out.println("字段名称是："+name);
                    String method = changeName(name);//根据字段名称获取执行的方法
                    System.out.println("执行的方法是："+method);
                    String type = data.getColumnTypeName(i);//获取数据库中的字段的类型
                    System.out.println("数据库中的字段类型是："+type);
                    if(type.equalsIgnoreCase("int")){
                        object.getClass().getMethod(method,Integer.class).invoke(object,rs.getInt(name));

                    }else if(type.equalsIgnoreCase("varchar")){
                        object.getClass().getMethod(method,String.class).invoke(object,rs.getString(name));
                    }


                }
                System.out.println((Teacher)object);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
//创建一个静态的方法
    public static String  changeName(String name){
        return "set"+name.substring(0,1).toUpperCase()+name.substring(1);
    }


}

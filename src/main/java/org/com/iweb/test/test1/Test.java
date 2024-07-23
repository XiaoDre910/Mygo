package org.com.iweb.test.test1;

import org.com.iweb.test.dao.Impl.StudentDAOImpl;
import org.com.iweb.test.dao.StudentDAO;
import org.com.iweb.test.pojo.Student;

import java.sql.*;

/**
 * @author Xiao
 * @date 2024/7/22 下午2:11
 */

public class Test {
    public static void main(String[] args) {
/*        //驱动加载
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("驱动加载成功");
        //获取jdbc连接
        String url = "jdbc:mysql://localhost:3306/nuist?characterEncoding=utf8";
        String username = "root";
        String password = "a12345";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("获取连接成功");
        Statement s = null;

        try {
            s = connection.createStatement();
            //准备一个sql语句
            String sql = "insert into student(id) values('1')";
            //运行sql语句
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //驱动加载 连接获取*/
        Student s1 =new Student(1,"丁真","男",new Date(0),"理塘",124122551L);
        Student s2 =new Student(1,"王源","男",new Date(0),"长沙",124122551L);
        StudentDAO studentDAO = new StudentDAOImpl();
        studentDAO.insert(s1);
        studentDAO.update(s2);



    }
}

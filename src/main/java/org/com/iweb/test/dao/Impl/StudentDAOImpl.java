package org.com.iweb.test.dao.Impl;

import org.com.iweb.test.dao.StudentDAO;
import org.com.iweb.test.pojo.Student;
import org.com.iweb.test.test1.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiao
 * @date 2024/7/22 下午3:52
 */
public class StudentDAOImpl implements StudentDAO {

    @Override
    public void insert(Student student) {
        try(Connection c =DBUtil.getConnection();
            Statement s = c.createStatement()
            //1,传参麻烦
            //2，性能较差
            //3，存在sql注入攻击问题

            //PreparedStatement
            //先编译 后传参 效率更高
            //传参方便
            //能够有效防止sql注入攻击
        ) {
            String sql = "insert into student(name,gender,birthday,addr,qqnumber) " +
                    "values('%s','%s','%s','%s','%d')";
            sql=String.format(sql, student.getName(), student.getGender(), student.getBirthday(),student.getAddr(),student.getQqnumber());
            s.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findWithLimit(int start, int limit) {
        String sql = "select * from student limit ?,?";
        List<Student> list = new ArrayList<>();
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ){
            ps.setInt(1,start);
            ps.setInt(2,limit);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setBirthday(rs.getDate("birthday"));
                s.setAddr(rs.getString("addr"));
                s.setQqnumber(rs.getLong("qqnumber"));
                list.add(s);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list.isEmpty() ?null:list;
    }

    @Override
    public void update(Student student) {
        String sql = "update student set name=?,gender=?,birthday=?,addr=?,qqnumber=? where id=?";
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ps.setString(1, student.getName());
            ps.setString(2, student.getGender());
            ps.setDate(3, new Date(student.getBirthday().getTime()));
            ps.setString(4, student.getAddr());
            ps.setLong(5, student.getQqnumber());
            ps.setInt(6, student.getId());
            ps.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) {
        Connection c = null;
        String sql ="delete from student where id=?";
        try{
                c =DBUtil.getConnection();
                c.setAutoCommit(false);
                PreparedStatement ps = c.prepareStatement(sql);

            ps.setInt(1, id);
            ps.execute();
            c.commit();
        }catch (SQLException e) {
            try{
                c.rollback();
            }catch(SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Integer count() {
        String sql = "select count(*) from student";//阿巴阿巴
        try(
                Connection c =DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
                ){
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                //每次从结果集读取一行数据
                //根据读取的字段数据不同 使用不同的get
                //方法参数有两种
                //一种是获取的字段在查询结果种出现的顺序
                //可以不写字段出现的顺序而写字段名称
                return rs.getInt(1);//此处1是指从查询结果第一列获取值
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Student findById(Integer id) {
        String sql = "select * from student where id=?";
        Student student = null;
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
                ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                student = new Student();
                student.setId(id);
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddr(rs.getString("addr"));
                student.setQqnumber(rs.getLong("qqnumber"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        return findWithLimit(0,Integer.MAX_VALUE);
    }

    @Override
    public List<Student> findByNameLike(String name) {
        return findByNameLikeWithLimit(name,0,Integer.MAX_VALUE);
    }

    @Override

    public List<Student> findByNameLikeWithLimit(String name, int start, int limit) {
        String sql = "select * from student where name like concat('%',?,'%') limit ?,?";
        List<Student> list = new ArrayList<>();
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
                ){
            ps.setString(1,name);
            ps.setInt(2,start);
            ps.setInt(3,limit);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setBirthday(rs.getDate("birthday"));
                s.setAddr(rs.getString("addr"));
                s.setQqnumber(rs.getLong("qqnumber"));
                list.add(s);
            }
        }catch (SQLException e){e.printStackTrace();}
        return list.isEmpty() ?null:list;
    }
}

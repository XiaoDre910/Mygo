package org.com.iweb.test1;

import org.com.iweb.test.dao.Impl.StudentDAOImpl;
import org.com.iweb.test.dao.StudentDAO;
import org.com.iweb.test.pojo.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Xiao
 * @date 2024/7/23 上午11:02
 */
public class TestStudentDAO {
    private int id;
    private StudentDAO studentDAO;

    @Before
    public void init(){
        studentDAO = new StudentDAOImpl();
    }
    @Test
    public void testFindById(){
        Assert.assertNotNull(studentDAO.findById(1));
        System.out.println(studentDAO.findById(1));
        Student student = new Student();
        System.out.println(student);
    }
    @Test
    public void testCount(){
        System.out.println(studentDAO.count());
        Assert.assertEquals(4L,(long)studentDAO.count());
    }
    @Test
    public void testFindAll(){
        System.out.println(studentDAO.findAll());
        Assert.assertNotNull(studentDAO.findAll());
    }
    @Test
    public void testFindByNameLike(){
        System.out.println(studentDAO.findByNameLike("丁"));
        Assert.assertNotNull(studentDAO.findByNameLike("丁"));
    }
    @Test
    public void testFindByNameLikeWithLimit(){
        System.out.println(studentDAO.findWithLimit(1,3));
        Assert.assertNotNull(studentDAO.findWithLimit(1,3));
    }
    @Test
    public void testFindWithLimit(){
        System.out.println(studentDAO.findWithLimit(1,3));
        Assert.assertNotNull(studentDAO.findWithLimit(1,3));
    }
}

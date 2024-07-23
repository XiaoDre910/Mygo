package org.com.iweb.test.dao;

import org.com.iweb.test.pojo.Student;

import java.util.List;

/**
 * @author Xiao
 * @date 2024/7/22 下午3:34
 */
public interface StudentDAO {
    /**插入一个学生信息
     * @param student 要插入的学生信息，主键无需提供
     */
    void insert(Student student);

    /**根据学生的id修改学生的全部信息
     * @param student 提供了要修改的学生id和需要被修改的字段值
     */
    void update(Student student);

    /**删除一个学生的信息
     * @param id 要删除的学生信息的id
     */
    void delete(Integer id);

    /**统计表中记录总数
     * @return 返回表中记录总数，如果为0 说明没有数据
     */
    Integer count();//select count(*);

    /**根据id查找对应学生信息
     * @param id 指定学生的id
     * @return 返回学生的记录 如果对应的学生不存在 则返回null
     */
    Student findById(Integer id);

    /**查询所有学生的信息
     * @return 所有学生的信息
     */
    List<Student> findAll();

    /**根据提供关键字模糊查询学生信息
     * @param name 名字关键字
     * @return
     */
    List<Student> findByNameLike(String name);

    /** 根据关键字分页模糊查询学生信息
     * @param name 提供关键字
     * @param start limit起始参数
     * @param limit 要分页查询的行数
     * @return 分页模糊查询的学生数据结果 若找不到 返回为空
     */
    List<Student> findByNameLikeWithLimit(String name,int start,int limit);

    /** 分页查询学生信息
     * @param start limit起始参数
     * @param limit 要分页查询的行数
     * @return
     */
    List<Student> findWithLimit(int start,int limit);
}

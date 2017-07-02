package com.cql.course.service;

import java.util.List;

import com.cql.course.dao.StudentDao;
import com.cql.course.vo.Course;
import com.cql.course.vo.Student;

public class StudentService {
	StudentDao stuDao;
	
	public StudentService(){
		stuDao = new StudentDao();
	}
	
	/**
	 * 显示课程信息
	 * @return
	 */
	public List<Course> courseShow(){
		return stuDao.showCourse();
	}
	
	/**
	 * 验证学生登录信息
	 * @param sid
	 * @param pass
	 * @return
	 */
	public boolean stuLog(int sid,String pass){
		return stuDao.loginStu(sid, pass);
	}
	
	/**
	 * 添加注册信息
	 * @param stu
	 * @return
	 */
	public boolean register(Student stu){
		return stuDao.reg(stu);
	}
	
	/**
	 * 选课
	 * @param cid
	 * @return
	 */
	public List<Course> selectCourse(int sid){
		return stuDao.selectCou(sid);
	}
	
	/**
	 * 选课完成往联系表中插入数据
	 * @param sid
	 * @param cid
	 * @return
	 */
	public boolean scAdd(int sid, int cid){
		return stuDao.scInsert(sid, cid);
	}
	
	/**
	 * 查询联系表数据，判断是否选过课
	 * @param sid
	 * @param cid
	 * @return
	 */
	public boolean scQuery(int sid, int cid){
		return stuDao.scSelect(sid, cid);
	}
	
	/**
	 * 管理员修改课程
	 * @param c
	 * @return
	 */
	public boolean adminUpd(Course c){
		return stuDao.addUpdate(c);
	}
	
	/**
	 * 管理员添加课程
	 * @param c
	 * @return
	 */
	public boolean adminAdd(Course c){
		return stuDao.aiminInsert(c);
	}
	
	/**
	 * 管理员删除课程
	 * @param cid
	 * @return
	 */
	public boolean adminDel(int cid){
		return stuDao.adminDelete(cid);
	}
	
	/**
	 * 学生对已选课程退选
	 * @param sid
	 * @param cid
	 * @return
	 */
	public boolean stuDelete(int sid,int cid){
		return stuDao.stuDel(sid, cid);
	}
	
	/**
	 * 管理员修改课程时查看课程是否正在开课
	 * @param cid
	 * @return
	 */
	public boolean isSel(int cid){
		return stuDao.isSelect(cid);
	}
	
	
}

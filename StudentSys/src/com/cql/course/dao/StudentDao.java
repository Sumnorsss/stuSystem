package com.cql.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.cql.core.dbutil.DBUtil;
import com.cql.course.vo.Course;
import com.cql.course.vo.Student;

public class StudentDao {

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	HttpSession session;

	/**
	 * 显示课程信息
	 * 
	 * @return
	 */
	public List<Course> showCourse() {
		List<Course> list = new ArrayList<Course>();
		conn = DBUtil.getConnection();
		String sql = "select * from course where 1 = 1";

		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Course c = new Course();
				c.setcId(rs.getInt("cid"));
				c.setcName(rs.getString("cname"));
				c.setTeacher(rs.getString("teacher"));
				list.add(c);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(conn, pst, rs);
		}
	}

	/**
	 * 验证学生身份
	 * 
	 * @param sid
	 * @param pass
	 * @return
	 */
	public boolean loginStu(int sid, String pass) {
		conn = DBUtil.getConnection();
		String sql = "select sid,pwd from student where sid = ? and pwd = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, sid);
			pst.setString(2, pass);

			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}else{
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.closeAll(conn, pst, rs);
		}
	}

	/**
	 * 将注册信息插入数据库
	 * 
	 * @param stu
	 * @return
	 */
	public boolean reg(Student stu) {

		conn = DBUtil.getConnection();
		String sql = "insert into student values(?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, stu.getSid());
			pst.setString(2, stu.getPwd());
			pst.setString(3, stu.getSname());
			pst.setString(4, stu.getSclass());
			pst.setString(5, stu.getAcdemy());

			pst.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 显示选课信息
	 * 
	 * @param cid
	 * @return
	 */
	public List<Course> selectCou(int sid) {
		List<Course> list = new ArrayList<>();

		conn = DBUtil.getConnection();
		String sql = "select c.cid, cname, teacher from course c,stu_course sc,student stu "
				+ "where c.cid=sc.cid AND stu.sid=sc.sid and stu.sid=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, sid);
			rs = pst.executeQuery();
			while (rs.next()) {
				Course c = new Course();
				c.setcId(rs.getInt("cid"));
				c.setcName(rs.getString("cname"));
				c.setTeacher(rs.getString("teacher"));
				list.add(c);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(conn, pst, rs);
		}

	}

	/**
	 * 选课完成往学生课程联系表中插入学号和课程号
	 * 
	 * @param sid
	 * @param cid
	 * @return
	 */
	public boolean scInsert(int sid, int cid) {
		conn = DBUtil.getConnection();
		String sql = "insert into stu_course(sid,cid) values(?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, sid);
			pst.setInt(2, cid);

			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 判断是否选过这门课
	 * 
	 * @param sid
	 * @param cid
	 * @return 如果选过就返回true,如果没选过就返回false
	 */
	public boolean scSelect(int sid, int cid) {
		conn = DBUtil.getConnection();
		String sql = "select * from stu_course where sid=? and cid=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, sid);
			pst.setInt(2, cid);

			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 管理员添加课程
	 * 
	 * @param c
	 * @return
	 */
	public boolean aiminInsert(Course c) {
		conn = DBUtil.getConnection();
		String sql = "insert into course values (?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, c.getcId());
			pst.setString(2, c.getcName());
			pst.setString(3, c.getTeacher());

			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.closeAll(conn, pst, rs);
		}
	}

	/**
	 * 管理员修改课程
	 * 
	 * @param c
	 * @return
	 */
	public boolean addUpdate(Course c) {
		conn = DBUtil.getConnection();
		String sql = "update course set cid=?,cname=?,teacher=? where cid=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, c.getcId());
			pst.setString(2, c.getcName());
			pst.setString(3, c.getTeacher());
			pst.setInt(4, c.getcId());

			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.closeAll(conn, pst, rs);
		}
	}

	/**
	 * 管理员删除课程
	 * @param cid
	 * @return
	 */
	public boolean adminDelete(int cid){
		conn = DBUtil.getConnection();
		String sql = "delete from course where cid = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 学生退选
	 * @param sid
	 * @param cid
	 * @return
	 */
	public boolean stuDel(int sid,int cid){
		conn = DBUtil.getConnection();
		String sql = "delete from stu_course where sid=? and cid=?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, sid);
			pst.setInt(2, cid);
			
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 管理员修改课程时查看课程是否正在开课
	 * @param cid
	 * @return
	 */
	public boolean isSelect(int cid){
		conn = DBUtil.getConnection();
		String sql = "select * from stu_course where cid=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	
	
}

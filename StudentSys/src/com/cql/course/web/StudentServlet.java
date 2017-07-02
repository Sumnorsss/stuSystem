package com.cql.course.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cql.course.service.StudentService;
import com.cql.course.vo.Course;
import com.cql.course.vo.Student;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentService stuSvr = new StudentService();
	HttpSession session; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String type = request.getParameter("type");
			// 注册模块
		if ("register".equals(type)) {
			register(request, response);
			// 验证登录
		} else if ("loginOk".equals(type)) {
			login(request, response);
			// 登录成功
		} else if ("loginShow".equals(type)) {
			loginShow(request, response);
			// 选课
		} else if ("selectC".equals(type)) {
			selctC(request, response);
			//删除所选课程
		} else if ("delete".equals(type)) {
			deleteCourse(request,response);
			//查看所选课程
		}else if("selectedShow".equals(type)){
			selectedShow(request, response);
			//管理员删除课程
		}else if("asDelete".equals(type)){
			asDelete(request, response);
			//管理员添加课程
		}else if("adminAdd".equals(type)){
			admAdd(request, response);
			//管理员修改课程
		}else if("adminUpdate".equals(type)){
			adminUpdate(request, response);
		}
		
		

	}
	/**
	 * 退选
	 * @param request
	 * @param response
	 */
	private void deleteCourse(HttpServletRequest request, HttpServletResponse response) {
		String cId = request.getParameter("cid");
		session = request.getSession();
		String sId = (String)session.getAttribute("username");
		if(cId != null && sId != null){
			int cid = Integer.parseInt(cId);
			int sid = Integer.parseInt(sId);
			stuSvr.stuDelete(sid, cid);
			
			List<Course> list = stuSvr.selectCourse(sid);
			System.out.println("delete: "+list);
			request.setAttribute("list", list);
			session.setAttribute("list", list);
			try {
				request.getRequestDispatcher("/selectCourse.jsp").forward(request, response);
				
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 选课
	 * 
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	private void selctC(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		String cId = request.getParameter("cid");
		session = request.getSession();
		String sId = (String) session.getAttribute("username");
		if (cId != null && sId != null) {
			try {

				int cid = Integer.parseInt(cId);
				int sid = Integer.parseInt(sId);

				boolean isChoose = stuSvr.scQuery(sid, cid);
				if (!isChoose) {

					stuSvr.scAdd(sid, cid);
					List<Course> list = stuSvr.selectCourse(sid);

					request.setAttribute("list", list);
					request.getRequestDispatcher("/selectCourse.jsp").forward(request, response);
				} else {
					response.getWriter().print("<script>您已选过该门课程，不可重复选择<script>");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
	
	/**
	 * 查看已选课程
	 * @param request
	 * @param response
	 */
	private void selectedShow(HttpServletRequest request, HttpServletResponse response){
		session = request.getSession();
		
		String sId = (String)session.getAttribute("username");
		System.out.println("sid:" + sId);
		
		if(sId != null){
			int sid = Integer.parseInt(sId);
			List<Course> list = stuSvr.selectCourse(sid);
			System.out.println("list" + list);
			
			request.setAttribute("list", list);
			try {
				request.getRequestDispatcher("/selectCourse.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			};
			
		}
	}

	/**
	 * 显示课程信息
	 * 
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	private void loginShow(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		

		List<Course> list = stuSvr.courseShow();

		request.setAttribute("list", list);

		try {
			request.getRequestDispatcher("/stuOk.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证登录身份
	 * 
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("logType");

		if (username != null && password != null) {
			if ("student".equals(role)) {
				int sid = Integer.parseInt(username);

				boolean isUser = stuSvr.stuLog(sid, password);
				// 学生登录
				if (isUser) {
					session = request.getSession();
					session.setAttribute("username", username);

					loginShow(request, response);
				}

				// 管理员登录
			} else if ("111".equals(username) && "root".equals(password) && "admin".equals(role)) {
				adminShow(request, response);
			} else {
				try {
					response.getWriter().println("用户名或密码错误");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		else {
			try {
				response.getWriter().println("用户名或密码不能为空");

				response.setHeader("Refresh", "3;url=login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 处理注册
	 * 
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	private void register(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {

		String stuId = request.getParameter("stuId");
		String pwd = request.getParameter("password");
		String stuName = request.getParameter("stuName");
		String stuClass = request.getParameter("stuClass");
		String acamedy = request.getParameter("acamedy");

		Student stu = new Student();
		if (stuId != null) {
			int sid = Integer.parseInt(stuId);
			stu.setSid(sid);
		}
		stu.setPwd(pwd);
		stu.setSname(stuName);
		stu.setSclass(stuClass);
		stu.setAcdemy(acamedy);

		boolean isAdd = stuSvr.register(stu);
		if (isAdd) {
			request.setAttribute("stu", stu);

			try {
				request.getRequestDispatcher("/registerOK.jsp").forward(request, response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				response.getWriter().println("注册失败");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	

	/**
	 * 管理员登录后的界面
	 * @param request
	 * @param response
	 */
	public void adminShow(HttpServletRequest request, HttpServletResponse response) {

		List<Course> list = stuSvr.courseShow();

		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 管理员删除课程
	 * @param request
	 * @param response
	 */
	public void asDelete(HttpServletRequest request,HttpServletResponse response){
//		session = request.getSession();
//		String cId = (String)session.getAttribute("cid");
		
		
		String cId = request.getParameter("cid");
		if(cId != null){
			int cid = Integer.parseInt(cId);
			boolean isSel = stuSvr.adminDel(cid);
			if(!isSel){
				try {
					response.getWriter().println("该门课正在开课，不可删除");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				
				List<Course> list = stuSvr.courseShow();
				request.setAttribute("list", list);
				try {
					request.getRequestDispatcher("/admin.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
	}
	
	/**
	 * 管理员添加课程
	 * @param request
	 * @param response
	 */
	private void admAdd(HttpServletRequest request, HttpServletResponse response){
		
		
		String cId = request.getParameter("cid");
		String cname = request.getParameter("cname");
		String  teacher = request.getParameter("teacher");
		
		Course c = new Course();
		if(cId != null){
			int cid = Integer.parseInt(cId);
			c.setcId(cid);
		}
		c.setcName(cname);
		c.setTeacher(teacher);
		stuSvr.adminAdd(c);
		
		List<Course> list = stuSvr.courseShow();
		
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 管理员修改课程
	 * @param request
	 * @param response
	 */
	public void adminUpdate(HttpServletRequest request, HttpServletResponse response){
		String cId = request.getParameter("cId");
		String cname = request.getParameter("cName");
		String  teacher = request.getParameter("cTeacher");
		
		Course c = new Course();
		int cid = 0;
		if(cId != null){
			cid = Integer.parseInt(cId);
			c.setcId(cid);
		}
		
		c.setcName(cname);
		c.setTeacher(teacher);
		boolean isSel = stuSvr.isSel(cid);
		System.out.println(isSel);
		if(!isSel){
			stuSvr.adminUpd(c);
			List<Course> list = stuSvr.courseShow();
			request.setAttribute("list", list);
			try {
				request.getRequestDispatcher("/admin.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().println("这门课程正在开课，不能修改");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

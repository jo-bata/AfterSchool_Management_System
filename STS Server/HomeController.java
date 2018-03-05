package com.skuniv.AfterSchoolServer;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	

	@RequestMapping(value = "/Login", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
	public @ResponseBody String Login(HttpServletRequest request, Model model) throws Exception {
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap();
		String id=null;
		String pw=null;
		String answer = "empty";
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		////////////////////

		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.

		try {
			
			// ArrayList<InfoStudent> studentList = new ArrayList<InfoStudent>();
			String url = "jdbc:mysql://192.168.0.6:3306/afterschool"; // ����Ϸ��� �����ͺ��̽����� ������ URL
			String db_id = "afterschool"; // ����� ����
			String db_pw = "qwerty1234!"; // ����� ������ �н�����
		
			// �����ͺ��̽��� �����ϱ� ���� DriverManager�� ����Ѵ�.
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, db_id, db_pw); // DriverManager ��ü�κ��� Connection ��ü�� ���´�.
			System.out.println("success connect");

		} catch (Exception e) { // ���ܰ� �߻��ϸ� ���� ��Ȳ�� ó���Ѵ�.
			System.out.println("failed connect");
			e.printStackTrace();
		}

		////
		java.sql.Statement stmt = conn.createStatement();
		String check = "select teacher_id,teacher_pw from teacher where teacher_id='"+id+"';";
		ResultSet rs = stmt.executeQuery(check);
		
		while (rs.next()) {
			String s_id = rs.getString("teacher_id"); // �ۼ���
			String s_pw = rs.getString("teacher_pw");
			
			System.out.println("rs check///"+s_id+"/"+s_pw);
			
			if(s_id.equals(id)&&s_pw.equals(pw))
				answer="login";
			else if(s_id.equals(id)&&!(s_id.equals(pw)))
				answer="misspw";
							
		}
		if(answer.equals("empty"))
			answer="missid";
		
//		Teacher teacher = new Teacher(t_tc, t_tn, t_tpn, t_te, t_tbd, t_ti, t_tp);	
		Teacher teacher = null;
		String myinfo = "select * from teacher where teacher_id='"+id+"';";
		rs = stmt.executeQuery(myinfo);
		
		while (rs.next()) {
			String t_tc = rs.getString("teacher_code"); // �ۼ���
			String t_tn = rs.getString("teacher_name");
			String t_tpn = rs.getString("teacher_phone_number");
			String t_te= rs.getString("teacher_email");
			String t_tbd = rs.getString("teacher_birth_date");
			String t_ti = rs.getString("teacher_id");
			String t_tp = rs.getString("teacher_pw");
			
			System.out.println("^_^");
			
			teacher = new Teacher(t_tc, t_tn, t_tpn, t_te, t_tbd, t_ti, t_tp);		
		}
		
		
		map.put("login", answer);
		map.put("teacher",teacher);
		
		
		stmt.close();
		conn.close();
		
		return URLEncoder.encode(gson.toJson(map), "utf-8");
	}
	
	
	@RequestMapping(value = "/Timetable", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
	public @ResponseBody String Timetable(HttpServletRequest request, Model model) throws Exception {
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap<String,Object>();
		String t_code=null;
		ArrayList<TimeTable> tt = new ArrayList<TimeTable>();
//		String answer = "empty";
		
		t_code = request.getParameter("t_code");
		////////////////////

		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.

		try {
			
			// ArrayList<InfoStudent> studentList = new ArrayList<InfoStudent>();
			String url = "jdbc:mysql://192.168.0.6:3306/afterschool"; // ����Ϸ��� �����ͺ��̽����� ������ URLㅁㄴㅇㅁㄴㅇ
			String db_id = "afterschool"; // ����� ����
			String db_pw = "qwerty1234!"; // ����� ������ �н�����
		
			// �����ͺ��̽��� �����ϱ� ���� DriverManager�� ����Ѵ�.
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, db_id, db_pw); // DriverManager ��ü�κ��� Connection ��ü�� ���´�.
			System.out.println("success connect");

		} catch (Exception e) { // ���ܰ� �߻��ϸ� ���� ��Ȳ�� ó���Ѵ�.
			System.out.println("failed connect");
			e.printStackTrace();
		}

		////
		java.sql.Statement stmt = conn.createStatement();
		String check = "select C.class_name,C.class_date,C.class_time,S.school_name from class C,school S where S.school_code=C.school_code and C.teacher_code = '"+t_code+"';";
		ResultSet rs = stmt.executeQuery(check);	
		
		TimeTable timetable= null;
		
		while (rs.next()) {
			String sn = rs.getString("S.school_name");
			String cn = rs.getString("C.class_name");
			int cd = rs.getInt("C.class_date");
			int ct = rs.getInt("C.class_time");
			
			
			timetable = new TimeTable(sn, cn, cd,ct);
			tt.add(timetable);
		}
		
		map.put("timetable", tt);
		
		
		stmt.close();
		conn.close();
		
		return URLEncoder.encode(gson.toJson(map), "utf-8");
	}
	
	@RequestMapping(value = "/Classinfo", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
	public @ResponseBody String Classinfo(HttpServletRequest request, Model model) throws Exception {
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap();
		ArrayList<Classinfo> arr= new ArrayList<Classinfo>();
		String t_code=null;
//		String answer = "empty";
		
		t_code = request.getParameter("t_code");
		
		////////////////////

		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.

		try {
			
			// ArrayList<InfoStudent> studentList = new ArrayList<InfoStudent>();
			String url = "jdbc:mysql://192.168.0.6:3306/afterschool"; // ����Ϸ��� �����ͺ��̽����� ������ URLㅁㄴㅇㅁㄴㅇ
			String db_id = "afterschool"; // ����� ����
			String db_pw = "qwerty1234!"; // ����� ������ �н�����
		
			// �����ͺ��̽��� �����ϱ� ���� DriverManager�� ����Ѵ�.
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, db_id, db_pw); // DriverManager ��ü�κ��� Connection ��ü�� ���´�.
			System.out.println("success connect");

		} catch (Exception e) { // ���ܰ� �߻��ϸ� ���� ��Ȳ�� ó���Ѵ�.
			System.out.println("failed connect");
			e.printStackTrace();
		}

		////
		java.sql.Statement stmt = conn.createStatement();
		String check = "select C.class_code,C.class_name,C.class_date,C.class_time,S.school_name,C.start_date,C.finish_date,T.textbook_name from class C,school S,textbook T where T.textbook_code=C.textbook_code and S.school_code=C.school_code and C.teacher_code='"+t_code+"';";
		ResultSet rs = stmt.executeQuery(check);	
		
		Classinfo classinfo= null;
		
		while (rs.next()) {
			String cc = rs.getString("C.class_code");
			String cn = rs.getString("C.class_name");
			int cd = rs.getInt("C.class_date");
			int ct = rs.getInt("C.class_time");
			String sn = rs.getString("S.school_name");
			SimpleDateFormat sd= new SimpleDateFormat("yyyy-MM-dd");
			String sd1 = sd.format(rs.getDate("C.start_date"));
			String sd2 = sd.format(rs.getDate("C.finish_date"));
			String tn = rs.getString("T.textbook_name");
			
			classinfo = new Classinfo(cc, cn, cd, ct, sn, sd1, sd2, tn);
			arr.add(classinfo);
		}
		
//		map.put("classinfo", arr);
		
		
		stmt.close();
		conn.close();
		
		return URLEncoder.encode(gson.toJson(arr), "utf-8");
	}
	
	@RequestMapping(value = "/Attendance", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
	public @ResponseBody String Attendance(HttpServletRequest request, Model model) throws Exception {
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap();
		ArrayList<Attendance> att = new ArrayList<Attendance>();
		String c_code=null;
//		String answer = "empty";
		
		c_code = request.getParameter("c_code");
			
		////////////////////

		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.

		try {
			
			String url = "jdbc:mysql://192.168.0.6:3306/afterschool"; // ����Ϸ��� �����ͺ��̽����� ������ URLㅁㄴㅇㅁㄴㅇ
			String db_id = "afterschool"; // ����� ����
			String db_pw = "qwerty1234!"; // ����� ������ �н�����
		
			// �����ͺ��̽��� �����ϱ� ���� DriverManager�� ����Ѵ�.
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, db_id, db_pw); // DriverManager ��ü�κ��� Connection ��ü�� ���´�.
			System.out.println("success connect");

		} catch (Exception e) { // ���ܰ� �߻��ϸ� ���� ��Ȳ�� ó���Ѵ�.
			System.out.println("failed connect");
			e.printStackTrace();
		}

		////
		java.sql.Statement stmt = conn.createStatement();
		String check = "select E.student_code,S.student_name from enroll E,student S where S.student_code=E.student_code and E.class_code='"+c_code+"';";
		ResultSet rs = stmt.executeQuery(check);	
		
		Attendance a ;
		while (rs.next()) {
			String sc = rs.getString("E.student_code");
			String sn = rs.getString("S.student_name");
			
			a = new Attendance(sc, sn,null);
			
			att.add(a);
		}
		
		
		Date nowdate = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sf.format(nowdate);
		
		String getdate= "select student_code,attendance_date_and_time from attendance where class_code='"+c_code+"';";
		rs = stmt.executeQuery(getdate);
		
		while (rs.next()) {
			String sc = rs.getString("student_code");
			Date adt = rs.getDate("attendance_date_and_time");
			
			
			String str = sf.format(adt);
			//System.out.println(str+"], , , ["+now);
			if(str.equals(now)) {
				for(int i=0;i<att.size();i++) {
					if(att.get(i).getSc().equals(sc)) {
						att.get(i).setAdt(str);
					}
				}
			}		
		}
		
//		map.put("attendance",att);
		
		
		stmt.close();
		conn.close();
		
		return URLEncoder.encode(gson.toJson(att), "utf-8");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/Attend", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
	public @ResponseBody String Attend(HttpServletRequest request, Model model) throws Exception {
		String answer;
		String c_code=null;
		String s_code=null;
		Long n_date = null;
		
		c_code = request.getParameter("c_code");
		s_code = request.getParameter("s_code");
		n_date = Long.parseLong(request.getParameter("n_code"));
		Date date= new Date();
		date.setTime(n_date);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String sd_date = sd.format(date);
		////////////////////

		Connection conn = null;

		try {
			
			String url = "jdbc:mysql://192.168.0.6:3306/afterschool"; 
			String db_id = "afterschool";
			String db_pw = "qwerty1234!";
		
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, db_id, db_pw); 
			System.out.println("success connect");

		} catch (Exception e) { 
			System.out.println("failed connect");
			e.printStackTrace();
		}

		////
		java.sql.Statement stmt = conn.createStatement();
		String check = "insert into attendance (class_code,student_code,attendance_date_and_time) values ('"+c_code+"','"+s_code+"','"+sd_date+"');";
		int rs = stmt.executeUpdate(check);
		
		stmt.close();
		conn.close();
		
		if(rs>0) {
			answer="true";
			return answer;
		}
		else {
			answer="false";
			return answer;
		}
		
		
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/Absent", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
	public @ResponseBody String Absent(HttpServletRequest request, Model model) throws Exception {
		String answer;
		String c_code=null;
		String s_code=null;
		Long n_date = null;
		
		c_code = request.getParameter("c_code");
		s_code = request.getParameter("s_code");
		n_date = Long.parseLong(request.getParameter("n_code"));
		Date date= new Date();
		date.setTime(n_date);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String sd_date = sd.format(date);
		////////////////////

		Connection conn = null;

		try {
			
			String url = "jdbc:mysql://192.168.0.6:3306/afterschool"; 
			String db_id = "afterschool";
			String db_pw = "qwerty1234!";
		
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, db_id, db_pw); 
			System.out.println("success connect");

		} catch (Exception e) { 
			System.out.println("failed connect");
			e.printStackTrace();
		}

		////
		System.out.println("날짜? : "+sd_date);
		java.sql.Statement stmt = conn.createStatement();
		String check = "delete from attendance where class_code='"+c_code+"' and student_code='"+s_code+"' and attendance_date_and_time='"+sd_date+"';";
		int rs = stmt.executeUpdate(check);
		
		stmt.close();
		conn.close();
		
		if(rs>0) {
			answer="true";
			return answer;
		}
		else {
			answer="false";
			return answer;
		}
		
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping(value = "/Studentinfo", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
	public @ResponseBody String Studentinfo(HttpServletRequest request, Model model) throws Exception {
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap();
//		ArrayList<Student> att = new ArrayList<Student>();
		String s_code=null;
//		String answer = "empty";
		
		s_code = request.getParameter("s_code");
			
		////////////////////

		Connection conn = null; // null�� �ʱ�ȭ �Ѵ�.

		try {
			
			String url = "jdbc:mysql://192.168.0.6:3306/afterschool"; // ����Ϸ��� �����ͺ��̽����� ������ URLㅁㄴㅇㅁㄴㅇ
			String db_id = "afterschool"; // ����� ����
			String db_pw = "qwerty1234!"; // ����� ������ �н�����
		
			// �����ͺ��̽��� �����ϱ� ���� DriverManager�� ����Ѵ�.
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, db_id, db_pw); // DriverManager ��ü�κ��� Connection ��ü�� ���´�.
			System.out.println("success connect");

		} catch (Exception e) { // ���ܰ� �߻��ϸ� ���� ��Ȳ�� ó���Ѵ�.
			System.out.println("failed connect");
			e.printStackTrace();
		}

		////
		java.sql.Statement stmt = conn.createStatement();
		String check = "select S.student_code,S.student_name,H.school_name,S.school_grade,S.school_class,S.student_birth_date,S.student_phone_number,S.protector_phone_number,S.voucher_program_state from student S,school H where S.school_code=H.school_code and S.student_code='"+s_code+"';";
		ResultSet rs = stmt.executeQuery(check);
		
		Student s = null ;
		while (rs.next()) {
			String sc = rs.getString("S.student_code");
			String sn = rs.getString("S.student_name");
			String scn = rs.getString("H.school_name");
			int g = rs.getInt("S.school_grade");
			int c = rs.getInt("S.school_class");
			String sbd = rs.getString("S.student_birth_date");
			String spn = rs.getString("S.student_phone_number");
			String ppn = rs.getString("S.protector_phone_number");
			int vps = rs.getInt("S.voucher_program_state");
			
			s = new Student(sc, sn, scn, g, c, sbd, spn, ppn, vps);
		}
		
		
//		map.put("student",s);
		
		
		stmt.close();
		conn.close();
		
		return URLEncoder.encode(gson.toJson(s), "utf-8");
	}
	
	@RequestMapping(value = "/InsertEnroll", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
	public @ResponseBody String Income(HttpServletRequest request, Model model) throws Exception {
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap();
		String s_code=null;
		String c_code=null;
		String answer = null;
		
		s_code = request.getParameter("s_code");
		c_code = request.getParameter("c_code");
			
		////////////////////

		Connection conn = null;

		try {
			
			String url = "jdbc:mysql://192.168.0.6:3306/afterschool"; 
			String db_id = "afterschool";
			String db_pw = "qwerty1234!";
		
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, db_id, db_pw); 
			System.out.println("success connect");

		} catch (Exception e) { 
			System.out.println("failed connect");
			e.printStackTrace();
		}

		////
		java.sql.Statement stmt = conn.createStatement();
		String check = "insert into enroll (class_code,student_code) values ('"+c_code+"','"+s_code+"');";
		int rs = stmt.executeUpdate(check);
		
		Student s = null ;
		if(rs>0) {
			answer="success";
		}
		else {
			answer="error";
		}
		
	
		
		
		stmt.close();
		conn.close();
		
		return answer;
	}
}

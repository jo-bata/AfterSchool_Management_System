package vo;

public class TeacherVO {
	private String teacher_code;
	private String teacher_name;
	private String teacher_phone_number;
	private String teacher_email;
	private String teacher_birth_date;
	private String teacher_id;
	private String teacher_pw;
	
	public TeacherVO(String tc, String tn, String tpn, String te, String tbd, String ti, String tp) {
		teacher_code=tc;
		teacher_name=tn;
		teacher_phone_number=tpn;
		teacher_email=te;
		teacher_birth_date=tbd;		teacher_id=ti;
		teacher_pw=tp;
	}
	
	public String getTeacher_code() {
		return teacher_code;
	}
	public void setTeacher_code(String teacher_code) {
		this.teacher_code = teacher_code;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getTeacher_phone_number() {
		return teacher_phone_number;
	}
	public void setTeacher_phone_number(String teacher_phone_number) {
		this.teacher_phone_number = teacher_phone_number;
	}
	public String getTeacher_email() {
		return teacher_email;
	}
	public void setTeacher_email(String teacher_email) {
		this.teacher_email = teacher_email;
	}
	public String getTeacher_birth_date() {
		return teacher_birth_date;
	}
	public void setTeacher_birth_date(String teacher_birth_date) {
		this.teacher_birth_date = teacher_birth_date;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getTeacher_pw() {
		return teacher_pw;
	}
	public void setTeacher_pw(String teacher_pw) {
		this.teacher_pw = teacher_pw;
	}
	
	
}

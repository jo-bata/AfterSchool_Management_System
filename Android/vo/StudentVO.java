package vo;

/**
 * Created by oopsla2 on 2017-11-29.
 */

public class StudentVO {
    String student_code;
    String student_name;
    String school_code;
    int school_grade;
    int school_class;
    String student_birth_date;
    String student_phone_number;
    String protector_phone_number;
    int voucher_program_state;

    public StudentVO(String student_code, String student_name, String school_code, int school_grade, int school_class, String student_birth_date, String student_phone_number, String protector_phone_number, int voucher_program_state) {
        this.student_code = student_code;
        this.student_name = student_name;
        this.school_code = school_code;
        this.school_grade = school_grade;
        this.school_class = school_class;
        this.student_birth_date = student_birth_date;
        this.student_phone_number = student_phone_number;
        this.protector_phone_number = protector_phone_number;
        this.voucher_program_state = voucher_program_state;
    }

    public boolean isVoucher_program_state() {
        return voucher_program_state==1?true:false;
    }
    public int getVoucher_program_state(){
        return voucher_program_state;
    }

    public void setVoucher_program_state(boolean voucher_program_state) {
      this.voucher_program_state = voucher_program_state==true?1:0;
    }

    public String getStudent_code() { return student_code; }

    public void setStudent_code(String student_code) { this.student_code = student_code; }

    public String getStudent_name() { return student_name; }

    public void setStudent_name(String student_name) { this.student_name = student_name; }

    public String getSchool_code() { return school_code; }

    public void setSchool_code(String school_code) { this.school_code = school_code; }

    public int getSchool_grade() { return school_grade; }

    public void setSchool_grade(int school_grade) { this.school_grade = school_grade; }

    public int getSchool_class() { return school_class; }

    public void setSchool_class(int school_class) { this.school_class = school_class; }

    public String getStudent_birth_date() { return student_birth_date; }

    public void setStudent_birth_date(String student_birth_date) { this.student_birth_date = student_birth_date; }

    public String getStudent_phone_number() { return student_phone_number; }

    public void setStudent_phone_number(String student_phone_number) { this.student_phone_number = student_phone_number; }

    public String getProtector_phone_number() { return protector_phone_number; }

    public void setProtector_phone_number(String protector_phone_number) { this.protector_phone_number = protector_phone_number; }
}

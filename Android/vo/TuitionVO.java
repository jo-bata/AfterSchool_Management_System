package vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LeeJaeJun on 2017-11-09.
 */

public class TuitionVO {
    Date payDate;

    String name;
    String phoneNumber;
    String className;
    String student_code;
    String class_code;

    public TuitionVO(String className, String name, String phoneNumber, String student_code, String class_code) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.className = className;
        this.student_code = student_code;
        this.class_code = class_code;
    }

    public TuitionVO(String className, String name, String phoneNumber, String student_code, String class_code, Date payDate) {
        this.payDate = payDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.className = className;
        this.student_code = student_code;
        this.class_code = class_code;
    }

    public boolean isPay() {
        return payDate==null?false:true;
    }

    public void setPay(Date date) {
        this.payDate = date;
    }

    public String getPayDate() {
        if(payDate==null)
            return "미 납 부";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(payDate);
    }
    public Date getPayDateRaw() {
        return payDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }
}

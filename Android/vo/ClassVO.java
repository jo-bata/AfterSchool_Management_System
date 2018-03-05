package vo;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by oopsla2 on 2017-11-29.
 */

public class ClassVO {
    String class_code;
    String teacher_code;
    String class_name;
    String school_code;
    int class_date;
    int class_time;
    String textbook_code;
    Date start_date;
    Date finish_date;
    int tuition;

    public ClassVO(String class_code, String teacher_code, String class_name, String school_code, int class_date, int class_time, String textbook_code, String start_date, String finish_date, int tuition) {
        this.class_code = class_code;
        this.teacher_code = teacher_code;
        this.class_name = class_name;
        this.school_code = school_code;
        this.class_date = class_date;
        this.class_time = class_time;
        this.textbook_code = textbook_code;
        this.tuition = tuition;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.start_date = simpleDateFormat.parse(start_date);
            this.finish_date = simpleDateFormat.parse(finish_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String getClass_code() { return class_code; }

    public void setClass_code(String class_code) { this.class_code = class_code; }

    public String getTeacher_code() { return teacher_code; }

    public void setTeacher_code(String teacher_code) { this.teacher_code = teacher_code; }

    public String getClass_name() { return class_name; }

    public void setClass_name(String class_name) { this.class_name = class_name; }

    public String getSchool_code() { return school_code; }

    public void setSchool_code(String school_code) { this.school_code = school_code; }

    public int getClass_date() { return class_date; }

    public void setClass_date(int class_date) { this.class_date = class_date; }

    public int getClass_time() { return class_time; }

    public void setClass_time(int class_time) { this.class_time = class_time; }

    public String getTextbook_code() { return textbook_code; }

    public void setTextbook_code(String textbook_code) { this.textbook_code = textbook_code; }

    public String getStart_date() {
//        return start_date;
        return new SimpleDateFormat("yyyy-MM-dd").format(start_date);
    }

    public void setStart_date(String start_date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.start_date = simpleDateFormat.parse(start_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getFinish_date() {
//        return finish_date;
        return new SimpleDateFormat("yyyy-MM-dd").format(finish_date);
    }

    public void setFinish_date(String finish_date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.finish_date = simpleDateFormat.parse(finish_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getTuition() { return tuition; }

    public void setTuition(int tuition) { this.tuition = tuition; }

    public void plusOneForGson(){
        start_date.setTime(start_date.getTime()+(1000*60*60*24));
        finish_date.setTime(finish_date.getTime()+(1000*60*60*24));
    }
}

package vo;

/**
 * Created by LeeJaeJun on 2017-11-01.
 */

public class CalendarVO {
    String schoolName;
    String className;

    public CalendarVO(String schoolName, String className) {
        this.schoolName = schoolName;
        this.className = className;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

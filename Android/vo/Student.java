package vo;

/**
 * Created by oopsla2 on 2017-11-09.
 */

public class Student {
    String name = null;
    String schoolName;
    int schoolGrade;
    int schoolClass;
    int birth;
    int phoneNumber;
    int momPhoneNumber;
    boolean voucherProgramState;
    boolean attendance;

    public Student(String name, boolean attendance) {
        this.name = name;
        this.attendance = attendance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolGrade() {
        return schoolGrade;
    }

    public void setSchoolGrade(int schoolGrade) {
        this.schoolGrade = schoolGrade;
    }

    public int getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(int schoolClass) {
        this.schoolClass = schoolClass;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMomPhoneNumber() {
        return momPhoneNumber;
    }

    public void setMomPhoneNumber(int momPhoneNumber) {
        this.momPhoneNumber = momPhoneNumber;
    }

    public boolean isVoucherProgramState() {
        return voucherProgramState;
    }

    public void setVoucherProgramState(boolean voucherProgramState) {
        this.voucherProgramState = voucherProgramState;
    }
}

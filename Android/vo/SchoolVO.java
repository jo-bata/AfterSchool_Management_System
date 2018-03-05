package vo;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

public class SchoolVO {
    String school_code;
    String school_name;
    String school_address;
    String school_phone_number;

    public SchoolVO(String school_code, String school_name, String school_address, String school_phone_number) {
        this.school_code = school_code;
        this.school_name = school_name;
        this.school_address = school_address;
        this.school_phone_number = school_phone_number;
    }

    public String getSchool_code() {
        return school_code;
    }

    public void setSchool_code(String school_code) {
        this.school_code = school_code;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getSchool_address() {
        return school_address;
    }

    public void setSchool_address(String school_address) {
        this.school_address = school_address;
    }

    public String getSchool_phone_number() {
        return school_phone_number;
    }

    public void setSchool_phone_number(String school_phone_number) {
        this.school_phone_number = school_phone_number;
    }
}

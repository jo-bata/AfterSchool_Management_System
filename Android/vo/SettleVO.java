package vo;

/**
 * Created by yjeong on 2017-11-16.
 */

public class SettleVO {
    String class_name, class_code;
    int tuition, textbook_price, student_cnt;
    private static final double TAX_PERCENTAGE = 0.2;

    public SettleVO(String class_code, String class_name, int tuition, int textbook_price, int student_cnt) {
        this.class_name = class_name;
        this.class_code = class_code;
        this.tuition = tuition;
        this.textbook_price = textbook_price;
        this.student_cnt = student_cnt;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getTuition() {
        return tuition;
    }

    public void setTuition(int tuition) {
        this.tuition = tuition;
    }

    public int getTextbook_price() {
        return textbook_price;
    }

    public void setTextbook_price(int textbook_price) {
        this.textbook_price = textbook_price;
    }

    public int getStudent_cnt() {
        return student_cnt;
    }

    public void setStudent_cnt(int student_cnt) {
        this.student_cnt = student_cnt;
    }

    public double getTax() {
        return tuition* student_cnt *TAX_PERCENTAGE;
    }

    public double getTotal() {
        return tuition* student_cnt -getTax();
    }
}

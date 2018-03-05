package vo;

/**
 * Created by oopsla2 on 2017-11-29.
 */

public class TextbookVO {
    String textbook_code;
    String textbook_name;
    int textbook_price;

    public TextbookVO(String textbook_code, String textbook_name, int textbook_price) {
        this.textbook_code = textbook_code;
        this.textbook_name = textbook_name;
        this.textbook_price = textbook_price;
    }

    public String getTextbook_code() { return textbook_code; }

    public void setTextbook_code(String textbook_code) { this.textbook_code = textbook_code; }

    public String getTextbook_name() { return textbook_name; }

    public void setTextbook_name(String textbook_name) { this.textbook_name = textbook_name; }

    public int getTextbook_price() { return textbook_price; }

    public void setTextbook_price(int textbook_price) { this.textbook_price = textbook_price; }
}

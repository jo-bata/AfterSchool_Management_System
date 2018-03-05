package vo;

import java.util.ArrayList;

/**
 * Created by LeeJaeJun on 2017-12-01.
 */

public class SettleBigVO {
    ArrayList<SettleVO> list;
    String year_month;

    public SettleBigVO(ArrayList<SettleVO> list, String year_month) {
        this.list = list;
        this.year_month = year_month;
    }

    public ArrayList<SettleVO> getList() {
        return list;
    }

    public void setList(ArrayList<SettleVO> list) {
        this.list = list;
    }

    public String getYear_month() {
        StringBuilder sb = new StringBuilder(year_month);
        int year = Integer.parseInt(sb.substring(0, 4));
        int month = Integer.parseInt(sb.substring(4, 6));
        return year+"년 "+month+"월";
    }

    public void setYear_month(String year_month) {
        this.year_month = year_month;
    }
}

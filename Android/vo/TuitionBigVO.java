package vo;

import java.util.ArrayList;

/**
 * Created by LeeJaeJun on 2017-11-09.
 */

public class TuitionBigVO {
    ArrayList<TuitionVO> list = new ArrayList<TuitionVO>();
    String yearMonth;

    public TuitionBigVO(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getYearMonth() {
        StringBuilder sb = new StringBuilder(yearMonth);
        int year = Integer.parseInt(sb.substring(0, 4));
        int month = Integer.parseInt(sb.substring(4, 6));
        return year+"년 "+month+"월";
    }

    public ArrayList<TuitionVO> getList() {
        return list;
    }
    public void addAll(ArrayList<TuitionVO> list){
        this.list.addAll(list);
    }
}

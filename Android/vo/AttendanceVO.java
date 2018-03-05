package vo;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendanceVO {
    private String sc;//학생코드
    private String sn;//학생이름
    private String adt=null;//출석일


    public AttendanceVO(String sc, String sn, String adt) {
        this.sc = sc;
        this.sn = sn;
        this.adt = adt;
    }

    public String getSc() {
        return sc;
    }
    public void setSc(String sc) {
        this.sc = sc;
    }
    public String getSn() {
        return sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }

    public Date getAdt() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(adt==null)
            return null;
        try {
            return sdf.parse(adt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setAdt(Date adt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(adt==null) {
            this.adt = null;
            return;
        }
        this.adt = sdf.format(adt);
    }
}

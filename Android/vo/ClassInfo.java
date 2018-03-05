package vo;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ClassInfo implements Parcelable{
    private String cc;//강의코드
    private String cn;//강의이름
    private int cd;//강의날짜
    private int ct;//강의시간
    private String sn;//학교이름
    private String sd;//강의시작일
    private String fd;//강의종강일
    private String tn;//교재이름



    public ClassInfo(String cc, String cn, int cd, int ct, String sn, String sd, String fd, String tn) {

        super();
        this.cc = cc;
        this.cn = cn;
        this.cd = cd;
        this.ct = ct;
        this.sn = sn;
        this.sd = sd;
        this.fd = fd;
        this.tn = tn;
    }

    protected ClassInfo(Parcel in) {
        cc = in.readString();
        cn = in.readString();
        cd = in.readInt();
        ct = in.readInt();
        sn = in.readString();
        sd = in.readString();
        fd = in.readString();
        tn = in.readString();
    }

    public static final Creator<ClassInfo> CREATOR = new Creator<ClassInfo>() {
        @Override
        public ClassInfo createFromParcel(Parcel in) {
            return new ClassInfo(in);
        }

        @Override
        public ClassInfo[] newArray(int size) {
            return new ClassInfo[size];
        }
    };

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public int getCt() {
        return ct;
    }

    public void setCt(int ct) {
        this.ct = ct;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getFd() {
        return fd;
    }

    public void setFd(String fd) {
        this.fd = fd;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cc);
        parcel.writeString(cn);
        parcel.writeInt(cd);
        parcel.writeInt(ct);
        parcel.writeString(sn);
        parcel.writeString(sd);
        parcel.writeString(fd);
        parcel.writeString(tn);
    }
}

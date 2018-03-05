package Server;

import android.app.Activity;

import java.util.Calendar;
import java.util.Date;

import adapter.AttendancePagerAdpater;
import vo.AttendanceVO;
import vo.ClassInfo;

/**
 * Created by LeeJaeJun on 2017-11-30.
 */

public class AttendanceDeleteServer extends ServerRootJun {
    AttendancePagerAdpater adpater;
    AttendanceVO attendanceVO;
    Activity activity;
    ClassInfo classInfo;
    public AttendanceDeleteServer(String URL, Activity activity, AttendancePagerAdpater adapter, ClassInfo classInfo, AttendanceVO attendanceVO) {
        super(URL);
        this.adpater = adapter;
        this.attendanceVO = attendanceVO;
        this.activity = activity;
        this.classInfo = classInfo;
    }

    @Override
    protected void afterResponse(String s) {
        attendanceVO.setAdt(null);
        adpater.notifyDataSetChanged();
    }

    @Override
    protected void setParameter() {
        map.put("c_code", classInfo.getCc());
        map.put("s_code", attendanceVO.getSc());
        map.put("n_code", String.valueOf(Calendar.getInstance().getTimeInMillis()));
    }
}

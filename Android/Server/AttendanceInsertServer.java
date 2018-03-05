package Server;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import adapter.AttendancePagerAdpater;
import vo.AttendanceVO;
import vo.ClassInfo;

/**
 * Created by LeeJaeJun on 2017-11-30.
 */

public class AttendanceInsertServer extends ServerRootJun {
    AttendancePagerAdpater adpater;
    AttendanceVO attendanceVO;
    Activity activity;
    ClassInfo classInfo;
    public AttendanceInsertServer(String URL, Activity activity, AttendancePagerAdpater adapter, ClassInfo classInfo, AttendanceVO attendanceVO) {
        super(URL);
        this.adpater = adapter;
        this.attendanceVO = attendanceVO;
        this.activity = activity;
        this.classInfo = classInfo;
    }

    @Override
    protected void afterResponse(String s) {
        boolean result =Boolean.valueOf(s);
        if(result){
            attendanceVO.setAdt(Calendar.getInstance().getTime());
            adpater.notifyDataSetChanged();
        }else{
            Toast.makeText(activity, "오류 발생", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void setParameter() {
        map.put("c_code", classInfo.getCc());
        map.put("s_code", attendanceVO.getSc());
        map.put("n_code", String.valueOf(Calendar.getInstance().getTimeInMillis()));
    }
}

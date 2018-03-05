package Server;

import android.util.Log;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import adapter.AttendancePagerAdpater;
import vo.AttendanceVO;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

public class AttendanceValueServer extends ServerRootJun {
    private String c_code;
    private AttendancePagerAdpater adpater;
    public AttendanceValueServer(String URL, AttendancePagerAdpater adapter, String c_code) {
        super(URL);
        this.c_code = c_code;
        this.adpater = adapter;
    }

    @Override
    protected void afterResponse(String s) {
        Gson gson = new Gson();
        AttendanceVO[] attendanceVOS = gson.fromJson(s, AttendanceVO[].class);
        adpater.addAll(attendanceVOS);
    }

    @Override
    protected void setParameter() {
        map.put("c_code", String.valueOf(c_code));
    }
}

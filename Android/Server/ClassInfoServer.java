package Server;

import com.example.leejaejun.afterschool.MainActivity;
import com.google.gson.Gson;

import adapter.ClassInfoPagerAdpater;
import adapter.FourthPagerAdpater;
import vo.AttendanceVO;
import vo.ClassInfo;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

public class ClassInfoServer extends ServerRootJun {
    ClassInfoPagerAdpater adapter;

    public ClassInfoServer(String URL, ClassInfoPagerAdpater adapter) {
        super(URL);
        this.adapter = adapter;
    }

    @Override
    protected void afterResponse(String s) {
        Gson gson = new Gson();
        ClassInfo[] classInfo = gson.fromJson(s, ClassInfo[].class);
        adapter.addAll(classInfo);
    }

    @Override
    protected void setParameter() {
        map.put("t_code", MainActivity.me.getTeacher_code());
    }
}

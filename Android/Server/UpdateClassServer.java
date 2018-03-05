package Server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import adapter.FourthPagerAdpater;
import util.FourthFragment;
import vo.ClassVO;

/**
 * Created by yjeong on 2017-11-30.
 */

public class UpdateClassServer extends ServerRootHyoen {
    ClassVO classVO;

    public UpdateClassServer(String URL, ClassVO classVO) {
        super(URL);
        this.classVO = classVO;
    }

    @Override
    protected void afterResponse(String s) {
        FourthPagerAdpater adpater = (FourthPagerAdpater) FourthFragment.newInstance("").getAdapter();
        adpater.notifyDataSetChanged();
    }

    @Override
    protected void setParameter() {
        map.put("class_code", classVO.getClass_code());
        map.put("teacher_code", classVO.getTeacher_code());
        map.put("class_name", classVO.getClass_name());
        map.put("school_code", classVO.getSchool_code());
        map.put("class_date", String.valueOf(classVO.getClass_date()));
        map.put("class_time", String.valueOf(classVO.getClass_time()));
        map.put("textbook_code", classVO.getTextbook_code());
        map.put("start_date", classVO.getStart_date());
        map.put("finish_date", classVO.getFinish_date());
        map.put("tuition", String.valueOf(classVO.getTuition()));
    }
}

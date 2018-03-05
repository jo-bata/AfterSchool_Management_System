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

public class InsertClassServer extends ServerRootHyoen {
    ClassVO classVO;

    public InsertClassServer(String URL,  ClassVO classVO) {
        super(URL);
        this.classVO = classVO;
    }

    @Override
    protected void afterResponse(String s) {
        FourthPagerAdpater adpater = (FourthPagerAdpater) FourthFragment.newInstance("").getAdapter();
        adpater.addAll(new ClassVO[]{classVO});
    }

    @Override
    protected void setParameter() {
        FourthPagerAdpater adpater = (FourthPagerAdpater) FourthFragment.newInstance("").getAdapter();
        ArrayList<ClassVO> list = adpater.getList();
        HashSet<Integer> set = new HashSet<>();
        Calendar today = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String newdate = sdf.format(today.getTime());
        for (int i = 0; i < list.size(); i++) {
            StringBuilder sb = new StringBuilder(list.get(i).getClass_code());
            String date = sb.substring(1, 7);
            int num = Integer.parseInt(sb.substring(7));
            if (date.equals(newdate))
                set.add(num);
        }
        int result = 1;
        while (set.contains(result))
            result++;
        String resultStr = "";
        StringBuilder sb = new StringBuilder(String.valueOf(result));
        int numOfZero = 2 - sb.length();
        for(int i = 0; i<numOfZero; i++)
            resultStr = "0" + resultStr;
        resultStr = "c" + newdate + resultStr + result;
        classVO.setClass_code(resultStr);

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

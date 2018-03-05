package Server;

import java.util.ArrayList;
import java.util.HashSet;

import adapter.SecondPagerAdpater;
import util.SecondFragment;
import vo.TeacherVO;

/**
 * Created by yjeong on 2017-11-30.
 */

public class InsertTeacherServer extends ServerRootHyoen{
    TeacherVO teacherVO;

    public InsertTeacherServer(String URL, TeacherVO teacherVO) {
        super(URL);
        this.teacherVO = teacherVO;
    }

    @Override
    protected void afterResponse(String s) {
       SecondPagerAdpater adpater = (SecondPagerAdpater) SecondFragment.newInstance("").getAdapter();
        adpater.addAll(new TeacherVO[]{teacherVO});
    }

    @Override
    protected void setParameter() {
        SecondPagerAdpater adpater = (SecondPagerAdpater)SecondFragment.newInstance("").getAdapter();
        ArrayList<TeacherVO> list = adpater.getList();
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i<list.size(); i++){
            StringBuilder sb = new StringBuilder(list.get(i).getTeacher_code());
            int num = Integer.parseInt(sb.substring(2));
            set.add(num);
        }
        int result = 1;
        while(set.contains(result))
            result++;
        String resultStr = "";
        StringBuilder sb = new StringBuilder(String.valueOf(result));
        int numOfZero = 2 - sb.length();
        for(int i = 0; i<numOfZero; i++)
            resultStr = "0" + resultStr;
        resultStr = "tc" + resultStr + result;
        teacherVO.setTeacher_code(resultStr);

        map.put("teacher_code",teacherVO.getTeacher_code());
        map.put("teacher_name",teacherVO.getTeacher_name());
        map.put("teacher_phone_number",teacherVO.getTeacher_phone_number());
        map.put("teacher_email",teacherVO.getTeacher_email());
        map.put("teacher_birth_date",teacherVO.getTeacher_birth_date());
        map.put("teacher_id",teacherVO.getTeacher_id());
        map.put("teacher_pw",teacherVO.getTeacher_pw());
    }
}

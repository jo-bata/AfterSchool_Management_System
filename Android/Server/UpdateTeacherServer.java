package Server;

import java.util.ArrayList;
import java.util.HashSet;

import adapter.SecondPagerAdpater;
import util.SecondFragment;
import vo.TeacherVO;

/**
 * Created by yjeong on 2017-11-30.
 */

public class UpdateTeacherServer extends ServerRootHyoen{
    TeacherVO teacherVO;

    public UpdateTeacherServer(String URL, TeacherVO teacherVO) {
        super(URL);
        this.teacherVO = teacherVO;
    }

    @Override
    protected void afterResponse(String s) {
       SecondPagerAdpater adpater = (SecondPagerAdpater) SecondFragment.newInstance("").getAdapter();
       adpater.notifyDataSetChanged();
    }

    @Override
    protected void setParameter() {
        map.put("teacher_code",teacherVO.getTeacher_code());
        map.put("teacher_name",teacherVO.getTeacher_name());
        map.put("teacher_phone_number",teacherVO.getTeacher_phone_number());
        map.put("teacher_email",teacherVO.getTeacher_email());
        map.put("teacher_birth_date",teacherVO.getTeacher_birth_date());
        map.put("teacher_id",teacherVO.getTeacher_id());
        map.put("teacher_pw",teacherVO.getTeacher_pw());
    }
}

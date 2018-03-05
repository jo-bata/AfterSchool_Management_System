package Server;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import adapter.ThirdPagerAdpater;
import util.ThirdFragment;
import vo.StudentVO;

/**
 * Created by yjeong on 2017-11-30.
 */

public class UpdateStudentServer extends ServerRootHyoen {
    StudentVO studentVO;

    public UpdateStudentServer(String URL, StudentVO studentVO) {
        super(URL);
        this.studentVO = studentVO;
    }

    @Override
    protected void afterResponse(String s) {
        ThirdPagerAdpater adpater = (ThirdPagerAdpater) ThirdFragment.newInstance("").getAdapter();
        adpater.notifyDataSetChanged();
    }

    @Override
    protected void setParameter() {
        map.put("student_code", studentVO.getStudent_code());
        map.put("student_name", studentVO.getStudent_name());
        map.put("school_grade", String.valueOf(studentVO.getSchool_grade()));

        map.put("school_code", studentVO.getSchool_code());
        map.put("school_class", String.valueOf(studentVO.getSchool_class()));
        map.put("student_birth_date", studentVO.getStudent_birth_date());
        map.put("student_phone_number", studentVO.getStudent_phone_number());

        map.put("protector_phone_number", studentVO.getProtector_phone_number());
        map.put("voucher_program_state", String.valueOf(studentVO.getVoucher_program_state()));
    }
}

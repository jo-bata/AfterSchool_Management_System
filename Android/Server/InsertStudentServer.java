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

public class InsertStudentServer extends ServerRootHyoen {
    StudentVO studentVO;

    public InsertStudentServer(String URL, StudentVO studentVO) {
        super(URL);
        this.studentVO = studentVO;
    }

    @Override
    protected void afterResponse(String s) {
        ThirdPagerAdpater adpater = (ThirdPagerAdpater) ThirdFragment.newInstance("").getAdapter();
        adpater.addAll(new StudentVO[]{studentVO});
    }

    @Override
    protected void setParameter() {
        ThirdPagerAdpater adpater = (ThirdPagerAdpater) ThirdFragment.newInstance("").getAdapter();
        ArrayList<StudentVO> list = adpater.getList();
        HashSet<Integer> set = new HashSet<>();
        Calendar today = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String newdate = sdf.format(today.getTime());
        for (int i = 0; i < list.size(); i++) {
            StringBuilder sb = new StringBuilder(list.get(i).getStudent_code());
            String date = sb.substring(2, 6);
            int num = Integer.parseInt(sb.substring(6));
            Log.d("어떻게 처리? : ", "["+date+"]"+"["+newdate+"]"+"["+num+"]");
            if (date.equals(newdate))
                set.add(num);
        }
        int result = 1;
        while (set.contains(result))
            result++;
        String resultStr = "";
        StringBuilder sb = new StringBuilder(String.valueOf(result));
        int numOfZero = 4 - sb.length();
        for(int i = 0; i<numOfZero; i++)
            resultStr = "0" + resultStr;
        resultStr = "st" + newdate + resultStr + result;
        studentVO.setStudent_code(resultStr);

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

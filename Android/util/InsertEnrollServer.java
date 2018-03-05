package util;

import android.app.AlertDialog;
import android.util.Log;
import android.widget.ArrayAdapter;

import Server.ServerRootJun;
import vo.StudentVO;

/**
 * Created by LeeJaeJun on 2017-12-01.
 */

public class InsertEnrollServer extends ServerRootJun {
    ArrayAdapter<StudentVO> adapter;
    String student_code, class_code;
    StudentVO studentVO;
    AlertDialog[] dialog;
    public InsertEnrollServer(String URL, String student_code, String class_code, ArrayAdapter<StudentVO> adapter, StudentVO studentVO, AlertDialog[] dialog) {
        super(URL);
        this.adapter = adapter;
        this.student_code = student_code;
        this.class_code = class_code;
        this.studentVO = studentVO;
        this.dialog = dialog;
    }

    @Override
    protected void afterResponse(String s) {
        adapter.add(studentVO);
        adapter.notifyDataSetChanged();
        dialog[0].dismiss();
    }

    @Override
    protected void setParameter() {
        Log.d("혹시 널? : ", class_code+", "+student_code);
        map.put("class_code", class_code);
        map.put("student_code", student_code);
    }
}

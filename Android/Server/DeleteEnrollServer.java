package Server;

import android.app.AlertDialog;
import android.widget.ArrayAdapter;

import vo.StudentVO;

/**
 * Created by LeeJaeJun on 2017-12-01.
 */

public class DeleteEnrollServer extends ServerRootHyoen {
    String classCode, studentCode;
    StudentVO studentVO;
    ArrayAdapter adapter;
    AlertDialog [] dialog;
    public DeleteEnrollServer(String URL, String classCode, String studentCode, ArrayAdapter adapter, StudentVO studentVO, AlertDialog[] dialog) {
        super(URL);
        this.classCode = classCode;
        this.studentCode = studentCode;
        this.studentVO = studentVO;
        this.adapter = adapter;
        this.dialog = dialog;
    }

    @Override
    protected void afterResponse(String s) {
        adapter.remove(studentVO);
        adapter.notifyDataSetChanged();
        dialog[0].dismiss();
    }

    @Override
    protected void setParameter() {
        map.put("class_code", classCode);
        map.put("student_code", studentCode);
    }
}

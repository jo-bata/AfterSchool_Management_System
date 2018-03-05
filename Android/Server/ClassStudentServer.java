package Server;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;

import vo.StudentVO;

/**
 * Created by LeeJaeJun on 2017-11-30.
 */

public class ClassStudentServer extends ServerRootHyoen {
    String class_code;
    ArrayAdapter<StudentVO> adapter;
    public ClassStudentServer(String URL, String class_code, ArrayAdapter<StudentVO> adapter) {
        super(URL);
        this.adapter = adapter;
        this.class_code = class_code;
    }

    @Override
    protected void afterResponse(String s) {
        Gson gson = new Gson();
        Log.d("온~다~", s);
        StudentVO[] studentVOS = gson.fromJson(s, StudentVO[].class);
        for(int i = 0; i<studentVOS.length; i++)
            adapter.add(studentVOS[i]);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void setParameter() {
        Log.d("간~다", "--"+class_code);
        map.put("class_code", class_code);
    }
}

package com.example.leejaejun.afterschool;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Server.AttendanceValueServer;
import adapter.AttendancePagerAdpater;
import vo.ClassInfo;
import vo.Student;

public class AttendanceValueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_value);
        ClassInfo classInfo = getIntent().getParcelableExtra("classInfo");
        AttendancePagerAdpater attendancePagerAdpater = new AttendancePagerAdpater(this, classInfo);
        ListView list_view = (ListView)findViewById(R.id.list_view);


        list_view.setAdapter(attendancePagerAdpater);
        new AttendanceValueServer(getString(R.string.junYoungURL)+"Attendance", attendancePagerAdpater, classInfo.getCc()).run();
    }

}

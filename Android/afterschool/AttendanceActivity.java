package com.example.leejaejun.afterschool;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import Server.ClassInfoServer;
import adapter.ClassInfoPagerAdpater;
import adapter.FourthPagerAdpater;
import vo.ClassInfo;

public class AttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        ListView listView = (ListView)findViewById(R.id.listView);

        final ClassInfoPagerAdpater adapter = new ClassInfoPagerAdpater(this);
        listView.setAdapter(adapter);

        new ClassInfoServer(getString(R.string.junYoungURL)+"Classinfo", adapter).run();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClassInfo classInfo = (ClassInfo)adapter.getItem(i);
                Intent intent = new Intent(getApplicationContext(), AttendanceValueActivity.class);
                intent.putExtra("classInfo", classInfo);
                startActivity(intent);
            }
        });
    }
}

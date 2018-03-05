package com.example.leejaejun.afterschool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import Server.TuitionServer;
import util.TuitionAdapter;
import vo.TuitionBigVO;
import vo.TuitionVO;

public class TuitionActivity extends AppCompatActivity {
    TuitionAdapter tuitionAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuition);
        tuitionAdapter = new TuitionAdapter(this);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(tuitionAdapter);

        new TuitionServer(getString(R.string.hyeonWooURL)+"payment", tuitionAdapter).run();
    }

}


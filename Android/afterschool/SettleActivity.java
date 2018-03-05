package com.example.leejaejun.afterschool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

import Server.SettleServer;
import vo.SettleVO;
import util.SettleAdapter;

public class SettleActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settle);
        TreeMap<String, ArrayList<SettleVO>> hashMap = new TreeMap<String, ArrayList<SettleVO>>();
        listView = (ListView) findViewById(R.id.list);


        SettleAdapter settleAdapter = new SettleAdapter(this);
        listView.setAdapter(settleAdapter);

        new SettleServer(getString(R.string.hyeonWooURL)+"settlement", hashMap, settleAdapter).run();
    }
}

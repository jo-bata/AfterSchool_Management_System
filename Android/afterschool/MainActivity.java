package com.example.leejaejun.afterschool;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import Server.AuthorityCheckServer;
import Server.TimeTableServer;
import util.CalendarBaseAdapter;
import vo.TeacherVO;

public class MainActivity extends AppCompatActivity {
    private static final int LOGIN_CODE = 0;
    public static TeacherVO me = null;

    CalendarBaseAdapter baseAdapter;
    GridView scheduleView;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(this, LogInActivity.class);
        startActivityForResult(intent, LOGIN_CODE);


        drawer = (DrawerLayout) findViewById(R.id.drawer);
        baseAdapter = new CalendarBaseAdapter(this);
        scheduleView = (GridView) findViewById(R.id.scheduleView);
        scheduleView.setAdapter(baseAdapter);
        baseAdapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView) findViewById(R.id.listView);

        String[] itmes = new String[]{"출석부", "납부확인", "결산관리"};

        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.text, itmes));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                switch (i) {
                    case 0:
                        intent = new Intent(getApplicationContext(), AttendanceActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), TuitionActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), SettleActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case LOGIN_CODE:
                    new TimeTableServer(this.getString(R.string.junYoungURL)+"Timetable", baseAdapter).run();
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawer.isDrawerOpen(Gravity.START)) {
                    drawer.closeDrawer(Gravity.START);
                } else {
                    drawer.openDrawer(Gravity.START);
                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;

import vo.CalendarVO;

/**
 * Created by LeeJaeJun on 2017-11-01.
 */

public class CalendarBaseAdapter extends BaseAdapter {

    public final static int MON = 1;
    public final static int TUE = 2;
    public final static int WED = 3;
    public final static int THU = 4;
    public final static int FRI = 5;
    CalendarVO[][]vos = new CalendarVO[11][5];
    Activity activity;
    Point point = new Point();
    public CalendarBaseAdapter(Activity activity) {
        this.activity = activity;
        WindowManager wm = (WindowManager)activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getSize(point);
    }

    @Override
    public int getCount() {
        return 12*6;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((point.x)/6, (point.y+25)/14);
        if(i==0) {
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setBackgroundColor(Color.parseColor("#FFCDD1F7"));
            linearLayout.setLayoutParams(params);
            return linearLayout;
        }
        if(i<6){
            TextView textView = new TextView(activity);
            textView.setGravity(Gravity.CENTER);
            String [] days = new String[]{"월", "화", "수", "목", "금"};
            textView.setText(days[i-1]);
            textView.setLayoutParams(params);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setBackgroundColor(Color.parseColor("#FFCDD1F7"));
            return textView;
        }
        if((i+1)%6==1){ // 6
            TextView textView = new TextView(activity);
            textView.setGravity(Gravity.CENTER);
            textView.setText(((i/6)+8)+" : 00");
            textView.setLayoutParams(params);
            textView.setBackgroundColor(Color.WHITE);
            return textView;
        }else{
            View v = activity.getLayoutInflater().inflate(R.layout.schedule_vo, null);
            CalendarVO one = null;
            if((one = vos[(i-7)/6][(i-7)%6])!=null){
                TextView schoolName = (TextView)v.findViewById(R.id.textView4);
                TextView className = (TextView)v.findViewById(R.id.textView5);
                schoolName.setText(one.getSchoolName());
                className.setText(one.getClassName());
                v.setBackgroundColor(Color.LTGRAY);
            }else
                v.setBackgroundColor(Color.WHITE);
            v.setLayoutParams(params);
            return v;
        }
    }
    public void addSchedule(int day, int timeOfDay, CalendarVO vo){
        vos[timeOfDay-9][day-1] = vo;
        notifyDataSetChanged();
    }
}

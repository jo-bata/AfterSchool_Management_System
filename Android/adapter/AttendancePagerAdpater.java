package adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeMap;

import Server.AttendanceDeleteServer;
import Server.AttendanceInsertServer;
import vo.AttendanceVO;
import vo.ClassInfo;
import vo.Student;
import vo.TeacherVO;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

public class AttendancePagerAdpater extends BaseAdapter {
    ArrayList<AttendanceVO> list = new ArrayList<AttendanceVO>();
    Activity activity;
    ClassInfo classInfo;
    public AttendancePagerAdpater(Activity activity, ClassInfo classInfo) {
        this.activity = activity;
        this.classInfo = classInfo;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
            view = activity.getLayoutInflater().inflate(R.layout.custom_listview, null);
        TextView numTv = (TextView)view.findViewById(R.id.num_tv);
        TextView nameTv = (TextView)view.findViewById(R.id.name_tv);
        final AttendanceVO teacherVOVO = list.get(i);
        numTv.setText(teacherVOVO.getSc());
        nameTv.setText(teacherVOVO.getSn());
        Log.d("값 : ? : ", i+", "+teacherVOVO.getSc()+", "+teacherVOVO.getAdt());
        final CheckBox att = (CheckBox)view.findViewById(R.id.attendance_cb);
        final CheckBox abs = (CheckBox)view.findViewById(R.id.absence_cb);
        setAttendance(teacherVOVO, att, abs, teacherVOVO.getAdt());
        att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AttendanceInsertServer(activity.getString(R.string.junYoungURL)+"Attend", activity, AttendancePagerAdpater.this,classInfo, teacherVOVO).run();
            }
        });
        abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AttendanceDeleteServer(activity.getString(R.string.junYoungURL)+"Absent", activity, AttendancePagerAdpater.this,classInfo, teacherVOVO).run();
            }
        });
        return view;
    }
    public void addAll(AttendanceVO[] array){
        for(int i = 0; i< array.length; i++)
            this.list.add(array[i]);
        notifyDataSetChanged();
    }
    private void setAttendance(AttendanceVO attendanceVO, CheckBox at, CheckBox ab, Date date){
        boolean now = date==null?false:true;
        at.setChecked(now);
        ab.setChecked(!now);
        attendanceVO.setAdt(date);

//        TreeMap<Integer, ArrayList<>> map = new TreeMap<Integer, ArrayList>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer integer, Integer t1) {
//                return compare(integer, t1);
//            }
//        });

    }
    //c20171103
    public void remove(AttendanceVO attendanceVO){
        list.remove(attendanceVO);
    }
}

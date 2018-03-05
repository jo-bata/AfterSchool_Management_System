package adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;

import java.util.ArrayList;

import vo.TeacherVO;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

public class SecondPagerAdpater extends RootNumAdapter {
    Activity activity;

    public SecondPagerAdpater(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = activity.getLayoutInflater().inflate(R.layout.secondpager, null);
        TextView first = (TextView) view.findViewById(R.id.textView10);
        TextView second = (TextView) view.findViewById(R.id.textView11);
        TextView third = (TextView) view.findViewById(R.id.textView19);
        TextView fourth = (TextView) view.findViewById(R.id.textView20);
        TextView fifth = (TextView) view.findViewById(R.id.textView21);
        TeacherVO teacherVOVO = (TeacherVO) list.get(i);
        first.setText(teacherVOVO.getTeacher_name());
        second.setText(teacherVOVO.getTeacher_email());
        third.setText(teacherVOVO.getTeacher_phone_number());
        fourth.setText(teacherVOVO.getTeacher_birth_date());
        fifth.setText(teacherVOVO.getTeacher_phone_number());
        return view;
    }

    @Override
    public boolean isSameCode(String code, int position) {
        TeacherVO teacherVO = (TeacherVO) list.get(position);
        return code.equals(teacherVO.getTeacher_code());
    }

    public void addAll(TeacherVO[] array) {
        for (int i = 0; i < array.length; i++) {
            if (!array[i].getTeacher_code().equals("tc00"))
                this.list.add(array[i]);
        }
        notifyDataSetChanged();
    }
}

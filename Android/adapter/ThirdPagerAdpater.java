package adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;

import java.util.ArrayList;

import vo.StudentVO;
import vo.TeacherVO;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

public class ThirdPagerAdpater extends RootNumAdapter{
    Activity activity;
    public ThirdPagerAdpater(Activity activity) {
        this.activity = activity;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public StudentVO getItem(int i) {
        return (StudentVO) list.get(i);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
            view = activity.getLayoutInflater().inflate(R.layout.secondpager, null);
        TextView first = (TextView)view.findViewById(R.id.textView10);
        TextView second = (TextView)view.findViewById(R.id.textView11);
        TextView third = (TextView)view.findViewById(R.id.textView19);
        TextView fourth = (TextView)view.findViewById(R.id.textView20);
        TextView fifth = (TextView)view.findViewById(R.id.textView21);
        StudentVO studentVO = (StudentVO) list.get(i);
        first.setText(studentVO.getStudent_name());

        second.setText(studentVO.getSchool_grade()+"학년");
        int grade = Integer.parseInt(studentVO.getStudent_birth_date().trim());
        third.setText(((grade/10000)<10?"0"+(grade/10000):(grade/10000))+"년 " +((grade/100)%100)+"월 "+ (grade%100)+" 일");
        fourth.setText(studentVO.getStudent_phone_number()+" 핸드폰");
        fifth.setText(studentVO.getProtector_phone_number()+" 부모님 핸드폰");
        return view;
    }

    @Override
    public boolean isSameCode(String code, int position) {
        StudentVO studentVO = (StudentVO) list.get(position);
        return code.equals(studentVO.getStudent_code());
    }

    public void addAll(StudentVO[] array){
        for(int i = 0; i< array.length; i++)
            this.list.add(array[i]);
        notifyDataSetChanged();
    }
    public void addAll(ArrayList<StudentVO> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void remove(int i){
        list.remove(i);
        notifyDataSetChanged();
    }
}

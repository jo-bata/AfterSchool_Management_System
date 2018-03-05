package adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;

import java.util.ArrayList;

import vo.ClassVO;
import vo.StudentVO;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

public class FourthPagerAdpater extends RootNumAdapter {
    Activity activity;
    public FourthPagerAdpater(Activity activity) {
        this.activity = activity;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public ClassVO getItem(int i) {
        return (ClassVO) list.get(i);
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
        ClassVO classVO = (ClassVO) list.get(i);
        first.setText(classVO.getClass_name());
        String []strs = new String[]{"월", "화", "수", "목", "금"};
        second.setText("매주 " + strs[classVO.getClass_date()-1]+"요일"+"   "+classVO.getClass_time()+"시");
        third.setText("시작일 : "+classVO.getStart_date());
        fourth.setText("종료일 : "+classVO.getFinish_date());
        fifth.setText(classVO.getClass_code());
        return view;
    }

    @Override
    public boolean isSameCode(String code, int position) {
        ClassVO classVO = (ClassVO) list.get(position);
        return code.equals(classVO.getClass_code());
    }

    public void addAll(ClassVO[] array){
        for(int i = 0; i< array.length; i++)
            this.list.add(array[i]);
        notifyDataSetChanged();
    }
}

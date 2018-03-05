package adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;

import java.util.ArrayList;

import vo.ClassInfo;
import vo.ClassVO;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

public class ClassInfoPagerAdpater extends BaseAdapter {
    ArrayList<ClassInfo> list = new ArrayList<ClassInfo>();
    Activity activity;
    public ClassInfoPagerAdpater(Activity activity) {
        this.activity = activity;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int i) {
        return list.get(i);
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
        ClassInfo classInfo = list.get(i);
        first.setText(classInfo.getCn());
        String []strs = new String[]{"월", "화", "수", "목", "금"};
        second.setText("매주 " + strs[classInfo.getCd()-1]+"요일"+"   "+ classInfo.getCt()+"시");
        third.setText("시작일 : "+ classInfo.getSd());
        fourth.setText("종료일 : "+ classInfo.getFd());
        fifth.setText("");
        return view;
    }
    public void addAll(ClassInfo[] array){
        for(int i = 0; i< array.length; i++)
            this.list.add(array[i]);
        notifyDataSetChanged();
    }
}

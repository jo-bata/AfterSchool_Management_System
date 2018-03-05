package adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;

import java.util.ArrayList;

import vo.SchoolVO;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

public class FirstPagerAdpater extends RootNumAdapter {
    Activity activity;

    public FirstPagerAdpater(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
            view = activity.getLayoutInflater().inflate(R.layout.firstpager, null);
        TextView first = (TextView)view.findViewById(R.id.textView6);
        TextView second = (TextView)view.findViewById(R.id.textView8);
        TextView third = (TextView)view.findViewById(R.id.textView9);
        SchoolVO schoolVO = (SchoolVO) list.get(i);
        first.setText(schoolVO.getSchool_name());
        second.setText(schoolVO.getSchool_address());
        third.setText(schoolVO.getSchool_phone_number());
        return view;
    }

    @Override
    public boolean isSameCode(String code, int position) {
        SchoolVO schoolVO = (SchoolVO) list.get(position);
        return code.equals(schoolVO.getSchool_code());
    }

    public void addAll(SchoolVO[] array){
        for(int i = 0; i< array.length; i++)
            this.list.add(array[i]);
        notifyDataSetChanged();
    }
}

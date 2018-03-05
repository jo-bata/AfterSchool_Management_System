package adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;

import java.util.ArrayList;

import vo.StudentVO;
import vo.TextbookVO;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

public class FifthPagerAdpater extends RootNumAdapter {
    Activity activity;
    public FifthPagerAdpater(Activity activity) {
        this.activity = activity;
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
            view = activity.getLayoutInflater().inflate(R.layout.secondpager, null);
        TextView first = (TextView)view.findViewById(R.id.textView10);
        TextView second = (TextView)view.findViewById(R.id.textView11);
        TextView third = (TextView)view.findViewById(R.id.textView19);
        TextView fourth = (TextView)view.findViewById(R.id.textView20);
        TextView fifth = (TextView)view.findViewById(R.id.textView21);
        TextbookVO textbookVO = (TextbookVO) list.get(i);
        first.setText(textbookVO.getTextbook_name());
        third.setText(textbookVO.getTextbook_price()+"원");
        return view;
    }

    @Override
    public boolean isSameCode(String code, int position) {
        TextbookVO textbookVO = (TextbookVO) list.get(position);
        return code.equals(textbookVO.getTextbook_code());
    }

    public void addAll(TextbookVO[] array){
        for(int i = 0; i< array.length; i++)
            this.list.add(array[i]);
        notifyDataSetChanged();
    }
}

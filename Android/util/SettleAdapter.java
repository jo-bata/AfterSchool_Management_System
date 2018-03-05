package util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;

import java.util.ArrayList;

import vo.SettleBigVO;
import vo.SettleVO;

public class SettleAdapter extends BaseAdapter {
    private Context context = null;
    private ArrayList<SettleBigVO> list = new ArrayList<SettleBigVO>();
    public SettleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SettleBigVO settleBigVO = list.get(position);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new TextView(context);
        textView.setText(settleBigVO.getYear_month());
        textView.setTextSize(23);
        LinearLayout.LayoutParams rootParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootParams.gravity = Gravity.CENTER;
        linearLayout.setLayoutParams(rootParams);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);
        textView.setLayoutParams(params);
        textView.setTextColor(Color.BLACK);
        linearLayout.addView(textView);
        ArrayList<SettleVO> insideList = settleBigVO.getList();
        for(int i = 0; i<insideList.size(); i++){
            Activity activity = (Activity)context;
            SettleVO settleVO = insideList.get(i);
            View view = activity.getLayoutInflater().inflate(R.layout.list, null);
            TextView classname = (TextView) view.findViewById(R.id.item1);
            classname.setText(settleVO.getClass_name());
            TextView tuition = (TextView) view.findViewById(R.id.item2);
            tuition.setText(String.valueOf(settleVO.getTuition()));
            TextView book = (TextView) view.findViewById(R.id.item3);
            book.setText(String.valueOf(settleVO.getTextbook_price()));
            TextView number = (TextView) view.findViewById(R.id.item4);
            number.setText(String.valueOf(settleVO.getStudent_cnt()));
            TextView tax = (TextView) view.findViewById(R.id.item5);
            tax.setText(String.valueOf(settleVO.getTax()));
            TextView total = (TextView) view.findViewById(R.id.item6);
            total.setText(String.valueOf(settleVO.getTotal()));
            linearLayout.addView(view);
        }
        return linearLayout;
    }

    public void add(SettleBigVO settleBigVO){
        list.add(settleBigVO);
    }
}

package util;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;

import java.util.ArrayList;

import Server.TuitionOkServer;
import vo.TuitionBigVO;
import vo.TuitionVO;

/**
 * Created by LeeJaeJun on 2017-11-09.
 */

public class TuitionAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<TuitionBigVO> list = new ArrayList<TuitionBigVO>();

    public TuitionAdapter(Activity activity) {
        this.activity = activity;
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
            view = activity.getLayoutInflater().inflate(R.layout.tuition_big_vo, null);
        LinearLayout listsLayout = view.findViewById(R.id.listsLayout);
        listsLayout.removeAllViews();
        TextView yearMonthText = view.findViewById(R.id.textView15);
        yearMonthText.setText(list.get(i).getYearMonth());
        ArrayList<TuitionVO> insideList = list.get(i).getList();
        for(int z = 0; z<insideList.size(); z++){
            View insideView = activity.getLayoutInflater().inflate(R.layout.tuition_vo, null);
            final TuitionVO tuitionVO = insideList.get(z);
            TextView classText = insideView.findViewById(R.id.textView);
            TextView nameText = insideView.findViewById(R.id.textView14);
            TextView phoneText = insideView.findViewById(R.id.textView16);

            classText.setText(tuitionVO.getClassName());
            nameText.setText(tuitionVO.getName());
            phoneText.setText(tuitionVO.getPhoneNumber());

            final ImageButton payButton = insideView.findViewById(R.id.button4);
            final TextView payText = insideView.findViewById(R.id.textView17);

            payButton.setImageResource(!tuitionVO.isPay()?R.drawable.unchecked :R.drawable.checked);
            payButton.setClickable(!tuitionVO.isPay()?true:false);
            View.OnClickListener listener = new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    final AlertDialog []dialog = new AlertDialog[1];
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    View v = activity.getLayoutInflater().inflate(R.layout.ensureview, null);
                    TextView text = (TextView)v.findViewById(R.id.textView);
                    text.setText("납부를 확인하시겠습니까?");
                    Button okButton = (Button)v.findViewById(R.id.okButton);
                    Button cancelButton = (Button)v.findViewById(R.id.cancelButton);
                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new TuitionOkServer(activity.getString(R.string.hyeonWooURL)+"updatePayment", payButton, tuitionVO, dialog, payText).run();
                        }
                    });
                    cancelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog[0].dismiss();
                        }
                    });
                    builder.setView(v);
                    dialog[0] = builder.show();
                }
            };
            if(!tuitionVO.isPay()){
                payButton.setOnClickListener(listener);
            }else{
                payButton.setOnClickListener(null);
            }
            payText.setText(tuitionVO.getPayDate());
            listsLayout.addView(insideView);
        }
        return view;
    }

    public void addOne(TuitionBigVO tuitionBigVO){
        list.add(tuitionBigVO);
    }
}

package Server;

import android.app.AlertDialog;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import vo.TuitionVO;

/**
 * Created by LeeJaeJun on 2017-12-01.
 */

public class TuitionOkServer extends ServerRootHyoen {
    TuitionVO tuitionVO;
    Calendar calendar;
    ImageButton payButton;
    TextView textView;
    AlertDialog [] dialog;
    public TuitionOkServer(String URL, ImageButton payButton, TuitionVO tuitionVO, AlertDialog[] dialog, TextView textView) {
        super(URL);
        this.tuitionVO = tuitionVO;
        this.payButton = payButton;
        this.dialog = dialog;
        this.textView = textView;
    }

    @Override
    protected void afterResponse(String s) {
        tuitionVO.setPay(calendar.getTime());
        payButton.setImageResource(R.drawable.checked);
        payButton.setClickable(false);
        dialog[0].dismiss();
        textView.setText(tuitionVO.getPayDate());
    }

    @Override
    protected void setParameter() {
        map.put("class_code", tuitionVO.getClass_code());
        map.put("student_code", tuitionVO.getStudent_code());
        calendar = Calendar.getInstance();
        map.put("tuition_date", new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
    }
}

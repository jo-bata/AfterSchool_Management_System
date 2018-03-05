package Server;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import adapter.FifthPagerAdpater;
import util.FifthFragment;
import vo.TextbookVO;

/**
 * Created by yjeong on 2017-11-30.
 */

public class InsertTextbookServer extends ServerRootHyoen {
    TextbookVO textbookVO;

    public InsertTextbookServer(String URL,  TextbookVO textbookVO) {
        super(URL);
        this.textbookVO = textbookVO;
    }

    @Override
    protected void afterResponse(String s) {
        Log.d("도착하였다", "--");
        FifthPagerAdpater adpater = (FifthPagerAdpater) FifthFragment.newInstance("").getAdapter();
        adpater.addAll(new TextbookVO[]{textbookVO});
    }

    @Override
    protected void setParameter() {
        Log.d("시작 되었다", "-_-");
        FifthPagerAdpater adpater = (FifthPagerAdpater) FifthFragment.newInstance("").getAdapter();
        ArrayList<TextbookVO> list = adpater.getList();
        HashSet<Integer> set = new HashSet<>();
        Calendar today = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String newdate = sdf.format(today.getTime());
        for (int i = 0; i < list.size(); i++) {
            StringBuilder sb = new StringBuilder(list.get(i).getTextbook_code());
            String date = sb.substring(2, 8);
            int num = Integer.parseInt(sb.substring(8));
            if (date.equals(newdate))
                set.add(num);
        }
        int result = 1;
        while (set.contains(result))
            result++;
        String resultStr = "";
        StringBuilder sb = new StringBuilder(String.valueOf(result));
        int numOfZero = 2 - sb.length();
        for(int i = 0; i<numOfZero; i++)
            resultStr = "0" + resultStr;
        resultStr = "tb" + newdate + resultStr + result;
        textbookVO.setTextbook_code(resultStr);

        map.put("textbook_code", textbookVO.getTextbook_code());
        map.put("textbook_name", textbookVO.getTextbook_name());
        map.put("textbook_price", String.valueOf(textbookVO.getTextbook_price()));
    }
}

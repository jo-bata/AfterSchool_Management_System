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

public class UpdateTextbookServer extends ServerRootHyoen {
    TextbookVO textbookVO;

    public UpdateTextbookServer(String URL, TextbookVO textbookVO) {
        super(URL);
        this.textbookVO = textbookVO;
    }

    @Override
    protected void afterResponse(String s) {
        FifthPagerAdpater adpater = (FifthPagerAdpater) FifthFragment.newInstance("").getAdapter();
        adpater.notifyDataSetChanged();
    }

    @Override
    protected void setParameter() {
        map.put("textbook_code", textbookVO.getTextbook_code());
        map.put("textbook_name", textbookVO.getTextbook_name());
        map.put("textbook_price", String.valueOf(textbookVO.getTextbook_price()));
    }
}

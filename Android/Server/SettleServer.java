package Server;

import android.util.Log;

import com.example.leejaejun.afterschool.MainActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import util.SettleAdapter;
import vo.SettleBigVO;
import vo.SettleVO;
import vo.TuitionVO;

/**
 * Created by LeeJaeJun on 2017-12-01.
 */

public class SettleServer extends ServerRootHyoen{

    TreeMap<String, ArrayList<SettleVO>> hashMap;
    SettleAdapter adapter;
    public SettleServer(String URL, TreeMap<String, ArrayList<SettleVO>> hashMap, SettleAdapter adapter) {
        super(URL);
        this.hashMap = hashMap;
        this.adapter = adapter;
    }

    @Override
    protected void afterResponse(String s) {
        Gson gson = new Gson();
        SettleTempVO[] tempVOS = gson.fromJson(s, SettleTempVO[].class);

        for(int i = 0; i<tempVOS.length; i++) {

            StringBuilder sb = new StringBuilder(tempVOS[i].class_code);
            String str = sb.substring(1, 7);

            if (!hashMap.containsKey(str))
                hashMap.put(str, new ArrayList<SettleVO>());
            hashMap.get(str).add(new SettleVO(tempVOS[i].class_code, tempVOS[i].class_name, tempVOS[i].tuition, tempVOS[i].textbook_price, tempVOS[i].student_cnt));
        }
        for(String str : hashMap.keySet()){
            adapter.add(new SettleBigVO(hashMap.get(str), str));
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void setParameter() {
        map.put("teacher_code", MainActivity.me.getTeacher_code());
    }
    class SettleTempVO{
            String class_code, class_name;
            int tuition, textbook_price, student_cnt;
    }
}

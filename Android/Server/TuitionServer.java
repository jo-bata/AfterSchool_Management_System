package Server;

import android.util.Log;

import com.example.leejaejun.afterschool.MainActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import util.TuitionAdapter;
import vo.TuitionBigVO;
import vo.TuitionVO;

/**
 * Created by LeeJaeJun on 2017-12-01.
 */

public class TuitionServer extends ServerRootHyoen {

    TuitionAdapter tuitionAdapter;
    public TuitionServer(String URL, TuitionAdapter tuitionAdapter) {
        super(URL);
        this.tuitionAdapter = tuitionAdapter;
    }

    @Override
    protected void afterResponse(String s) {
        Log.d("payment : ", s);
        Gson gson = new Gson();
        TuitionTempVO[] tempVOS = gson.fromJson(s, TuitionTempVO[].class);
        TreeMap<String, ArrayList<TuitionVO>> hashMap = new TreeMap<String, ArrayList<TuitionVO>>();
        for(int i = 0; i<tempVOS.length; i++){
            StringBuilder sb = new StringBuilder(tempVOS[i].class_code);
            String str = sb.substring(1, 7);
            if(!hashMap.containsKey(str))
                hashMap.put(str, new ArrayList<TuitionVO>());
            TuitionVO tuitionVO = null;
            if(tempVOS[i].getTuition_date()==null){
                tuitionVO = new TuitionVO(tempVOS[i].getClass_name(), tempVOS[i].getStudent_name(), tempVOS[i].getProtector_phone_number(), tempVOS[i].getStudent_code(), tempVOS[i].getClass_code());
            }else{
                tuitionVO = new TuitionVO(tempVOS[i].getClass_name(), tempVOS[i].getStudent_name(), tempVOS[i].getProtector_phone_number(), tempVOS[i].getStudent_code(), tempVOS[i].getClass_code(), tempVOS[i].getTuition_date());
            }
            hashMap.get(str).add(tuitionVO);
        }
        for(String str : hashMap.keySet()){
            TuitionBigVO tuitionBigVO = new TuitionBigVO(str);
            tuitionBigVO.addAll(hashMap.get(str));
            tuitionAdapter.addOne(tuitionBigVO);
        }
        tuitionAdapter.notifyDataSetChanged();
    }

    @Override
    protected void setParameter() {
        map.put("teacher_code", MainActivity.me.getTeacher_code());
    }
    class TuitionTempVO{
        String class_code;
        String class_name;
        String student_code;
        String protector_phone_number;
        String student_name;
        Date tuition_date;

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public String getClass_code() {
            return class_code;
        }

        public void setClass_code(String class_code) {
            this.class_code = class_code;
        }

        public String getStudent_code() {
            return student_code;
        }

        public void setStudent_code(String student_code) {
            this.student_code = student_code;
        }

        public String getProtector_phone_number() {
            return protector_phone_number;
        }

        public void setProtector_phone_number(String protector_phone_number) {
            this.protector_phone_number = protector_phone_number;
        }

        public String getStudent_name() {
            return student_name;
        }

        public void setStudent_name(String student_name) {
            this.student_name = student_name;
        }

        public Date getTuition_date() {
            return tuition_date;
        }

        public void setTuition_date(Date tuition_date) {
            this.tuition_date = tuition_date;
        }
    }
}

package Server;

import java.util.ArrayList;
import java.util.HashSet;

import adapter.FirstPagerAdpater;
import util.FirstFragment;
import vo.SchoolVO;

/**
 * Created by yjeong on 2017-11-29.
 */

public class InsertSchoolServer extends ServerRootHyoen {
    SchoolVO schoolVO;
    public InsertSchoolServer(String URL, SchoolVO schoolVO) {
        super(URL);
        this.schoolVO = schoolVO;
    }

    @Override
    protected void afterResponse(String s) {
        FirstPagerAdpater adpater = (FirstPagerAdpater)FirstFragment.newInstance("").getAdapter();
        adpater.addAll(new SchoolVO[]{schoolVO});
    }

    @Override
    protected void setParameter() {
        FirstPagerAdpater adpater = (FirstPagerAdpater) FirstFragment.newInstance("").getAdapter();
        ArrayList<SchoolVO> list = adpater.getList();
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i<list.size(); i++){
            StringBuilder sb = new StringBuilder(list.get(i).getSchool_code());
            int num = Integer.parseInt(sb.substring(2));
            set.add(num);
        }
        int result = 1;
        while(set.contains(result))
            result++;
        String resultStr = "";
        StringBuilder sb = new StringBuilder(String.valueOf(result));
        int numOfZero = 2 - sb.length();
        for(int i = 0; i<numOfZero; i++)
            resultStr = "0" + resultStr;
        resultStr = "sc" + resultStr + result;
        schoolVO.setSchool_code(resultStr);

        map.put("school_code",schoolVO.getSchool_code());
        map.put("school_name",schoolVO.getSchool_name());
        map.put("school_address",schoolVO.getSchool_address());
        map.put("school_phone_number",schoolVO.getSchool_phone_number());
    }
}

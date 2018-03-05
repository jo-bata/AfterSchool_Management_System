package Server;

import java.util.ArrayList;
import java.util.HashSet;

import adapter.FirstPagerAdpater;
import util.FirstFragment;
import vo.SchoolVO;

/**
 * Created by yjeong on 2017-11-29.
 */

public class UpdateSchoolServer extends ServerRootHyoen {
    SchoolVO schoolVO;
    public UpdateSchoolServer(String URL, SchoolVO schoolVO) {
        super(URL);
        this.schoolVO = schoolVO;
    }

    @Override
    protected void afterResponse(String s) {
        FirstPagerAdpater adpater = (FirstPagerAdpater)FirstFragment.newInstance("").getAdapter();
        adpater.notifyDataSetChanged();
    }

    @Override
    protected void setParameter() {
        map.put("school_code",schoolVO.getSchool_code());
        map.put("school_name",schoolVO.getSchool_name());
        map.put("school_address",schoolVO.getSchool_address());
        map.put("school_phone_number",schoolVO.getSchool_phone_number());
    }
}

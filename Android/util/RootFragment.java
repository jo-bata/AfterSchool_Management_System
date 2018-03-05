package util;


import android.support.v4.app.Fragment;
import android.widget.BaseAdapter;

import adapter.RootNumAdapter;

/**
 * Created by yjeong on 2017-11-30.
 */

public class RootFragment extends Fragment {
    private RootNumAdapter adapter;
    public RootNumAdapter getAdapter(){
        return adapter;
    }
    public void setAdapter(RootNumAdapter adapter){
        this.adapter = adapter;
    }

    public void refresh(){
    }
}

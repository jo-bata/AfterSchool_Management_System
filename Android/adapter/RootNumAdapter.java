package adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import vo.SchoolVO;
import vo.TextbookVO;

/**
 * Created by LeeJaeJun on 2017-12-01.
 */

public abstract class RootNumAdapter extends BaseAdapter {
    ArrayList<Object> list = new ArrayList<Object>();
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
        return null;
    }
    public void addAll(Object[] array){
        for(int i = 0; i< array.length; i++)
            this.list.add(array[i]);
        notifyDataSetChanged();
    }
    public ArrayList getList() {
        return list;
    }
    public void remove(String code){
        for(int i = 0; i<list.size(); i++){
            if(isSameCode(code, i)) {
                list.remove(i);
                break;
            }
        }
        notifyDataSetChanged();
    }
    public abstract boolean isSameCode(String code, int position);
}

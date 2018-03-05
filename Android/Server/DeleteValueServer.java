package Server;

import android.app.AlertDialog;
import android.widget.BaseAdapter;
import android.widget.ListView;

import adapter.FirstPagerAdpater;
import adapter.RootNumAdapter;
import util.FirstFragment;
import vo.SchoolVO;

/**
 * Created by yjeong on 2017-11-29.
 */

public class DeleteValueServer extends ServerRootHyoen {
    public static final String SCHOOL = "school";
    public static final String CLASS = "class";
    public static final String STUDENT = "student";
    public static final String TEXTBOOK = "textbook";
    public static final String TEACHER = "teacher";
    private String type;
    private String code;
    private RootNumAdapter adapter;
    private AlertDialog[] dialog;
    public DeleteValueServer(String URL, String type, String code, RootNumAdapter adapter, AlertDialog[] dialog) {
        super(URL);
        this.type = type;
        this.code = code;
        this.adapter = adapter;
        this.dialog = dialog;

    }

    @Override
    protected void afterResponse(String s) {
        adapter.remove(code);
        adapter.notifyDataSetChanged();
        dialog[0].dismiss();
    }

    @Override
    protected void setParameter() {
        map.put("delete_data", type);
        map.put("data_code", code);
    }
}

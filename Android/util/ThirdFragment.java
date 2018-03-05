package util;

/**
 * Created by Jun on 2017-11-09.
 */

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.leejaejun.afterschool.R;

import Server.DeleteValueServer;
import Server.GetVariousInfoServer;
import adapter.SecondPagerAdpater;
import adapter.ThirdPagerAdpater;
import vo.StudentVO;
import vo.TeacherVO;


public class ThirdFragment extends RootFragment {
    static ThirdFragment f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.second_frag, null) ;
        setAdapter(new ThirdPagerAdpater(getActivity()));
        new GetVariousInfoServer(getString(R.string.hyeonWooURL)+"necessaryData", getAdapter(), GetVariousInfoServer.STUDENT).run();

        ListView listview = (ListView) view.findViewById(R.id.secondListview) ;
        listview.setAdapter(getAdapter()) ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ThirdPagerAdpater adpater = (ThirdPagerAdpater)getAdapter();
                new CreateDialog(getActivity()).createView(2, adpater.getList().get(position));
            }
        }) ;
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final ThirdPagerAdpater adpater = (ThirdPagerAdpater)getAdapter();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View v = getActivity().getLayoutInflater().inflate(R.layout.ensureview, null);
                final AlertDialog [] dialog = new AlertDialog[1];
                Button button = (Button)v.findViewById(R.id.okButton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StudentVO schoolVO = (StudentVO) adpater.getList().get(i);
                        new DeleteValueServer(getString(R.string.hyeonWooURL)+"deleteData", DeleteValueServer.STUDENT, schoolVO.getStudent_code(), getAdapter(), dialog).run();
                    }
                });
                Button cancelButton = (Button)v.findViewById(R.id.cancelButton);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog[0].dismiss();
                    }
                });
                builder.setView(v);
                dialog[0] = builder.show();
                return true;
            }
        });
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_sample,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

//        switch (item.getItemId()){
//            case R.id.edit_btn:
//                //할일
//            case R.id.plus_btn:
//                //할일
//        }


    }

    public static ThirdFragment newInstance(String text) {
        if(f == null) {
            f = new ThirdFragment();
            Bundle b = new Bundle();
            b.putString("msg", text);

            f.setArguments(b);
        }
        return f;
    }
}

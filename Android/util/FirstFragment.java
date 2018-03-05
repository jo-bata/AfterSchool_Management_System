package util;

/**
 * Created by Jun on 2017-11-09.
 */

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

import Server.DeleteValueServer;
import Server.GetVariousInfoServer;
import adapter.FirstPagerAdpater;
import vo.SchoolVO;


public class FirstFragment extends RootFragment {
    static FirstFragment f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.first_frag, null);
        setAdapter(new FirstPagerAdpater(getActivity()));
        new GetVariousInfoServer(getString(R.string.hyeonWooURL)+"necessaryData", getAdapter(), GetVariousInfoServer.SCHOOL).run();

        final ListView listview = (ListView) view.findViewById(R.id.firstListview);
        listview.setAdapter(getAdapter());

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                FirstPagerAdpater adpater = (FirstPagerAdpater)getAdapter();
                new CreateDialog(getActivity()).createView(0, adpater.getList().get(position));
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final FirstPagerAdpater adpater = (FirstPagerAdpater)getAdapter();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View v = getActivity().getLayoutInflater().inflate(R.layout.ensureview, null);
                final AlertDialog [] dialog = new AlertDialog[1];
                Button button = (Button)v.findViewById(R.id.okButton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SchoolVO schoolVO = (SchoolVO) adpater.getList().get(i);
                        new DeleteValueServer(getString(R.string.hyeonWooURL)+"deleteData", DeleteValueServer.SCHOOL, schoolVO.getSchool_code(), getAdapter(), dialog).run();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_sample, menu);
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

    public static FirstFragment newInstance(String text) {
        if (f == null) {
            f = new FirstFragment();
            Bundle b = new Bundle();
            b.putString("msg", text);
            f.setArguments(b);

        }
        return f;
    }
}

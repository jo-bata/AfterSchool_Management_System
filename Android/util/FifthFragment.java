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
import adapter.FifthPagerAdpater;
import adapter.FourthPagerAdpater;
import vo.ClassVO;
import vo.TextbookVO;


public class FifthFragment extends RootFragment {
    static FifthFragment f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fourth_frag, null) ;
        setAdapter(new FifthPagerAdpater(getActivity()));
        new GetVariousInfoServer(getString(R.string.hyeonWooURL)+"necessaryData", getAdapter(), GetVariousInfoServer.TEXTBOOK).run();

        ListView listview = (ListView) view.findViewById(R.id.fourthListview) ;
        listview.setAdapter(getAdapter());

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                FifthPagerAdpater adpater = (FifthPagerAdpater)getAdapter();
                new CreateDialog(getActivity()).createView(4, adpater.getList().get(position));

                // TODO : use strText
            }
        }) ;
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final FifthPagerAdpater adpater = (FifthPagerAdpater)getAdapter();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View v = getActivity().getLayoutInflater().inflate(R.layout.ensureview, null);
                final AlertDialog [] dialog = new AlertDialog[1];
                Button button = (Button)v.findViewById(R.id.okButton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextbookVO schoolVO = (TextbookVO) adpater.getList().get(i);
                        new DeleteValueServer(getString(R.string.hyeonWooURL)+"deleteData", DeleteValueServer.TEXTBOOK, schoolVO.getTextbook_code(), getAdapter(), dialog).run();
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
//        }

    }

    public static FifthFragment newInstance(String text) {
        if(f == null) {
            f = new FifthFragment();
            Bundle b = new Bundle();
            b.putString("msg", text);

            f.setArguments(b);
        }
        return f;
    }

}
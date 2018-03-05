package util;

/**
 * Created by Jun on 2017-11-09.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import Server.ClassStudentServer;
import Server.DeleteEnrollServer;
import Server.DeleteValueServer;
import Server.GetVariousInfoServer;
import adapter.FourthPagerAdpater;
import adapter.ThirdPagerAdpater;
import vo.ClassVO;
import vo.StudentVO;
import vo.TextbookVO;


public class FourthFragment extends RootFragment {
   static FourthFragment f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.third_frag, null) ;
        setAdapter(new FourthPagerAdpater(getActivity()));
        new GetVariousInfoServer(getString(R.string.hyeonWooURL)+"necessaryData", getAdapter(), GetVariousInfoServer.CLASS).run();

        ListView listview = (ListView) view.findViewById(R.id.thirdListview) ;
        listview.setAdapter(getAdapter()) ;
        final FourthPagerAdpater fourthPagerAdpater = (FourthPagerAdpater)getAdapter();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final AlertDialog [] dialog = new AlertDialog[1];
                final ClassVO classVO = (ClassVO)fourthPagerAdpater.getItem(position);
                builder.setItems(new String[]{"수정", "학생검색"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, final int i) {
                        if(i==0){
                            new CreateDialog(getActivity()).createView(3, fourthPagerAdpater.getList().get(position));
                        }else if(i==1){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            View view = getActivity().getLayoutInflater().inflate(R.layout.class_list, null);
                            ListView selectedStudents = view.findViewById(R.id.listView);
                            FloatingActionButton button = view.findViewById(R.id.button);
                            final ArrayAdapter<StudentVO> adapter = new ArrayAdapter<StudentVO>(getActivity(), 0){
                                @NonNull
                                @Override
                                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                    LinearLayout linearLayout = new LinearLayout(getContext());
                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                                    params.setMargins(10,5,10,5);
                                    TextView codeText = new TextView(getContext());
                                    TextView nameText = new TextView(getContext());
                                    codeText.setTextSize(15);
                                    nameText.setTextSize(20);
                                    codeText.setLayoutParams(params);
                                    nameText.setLayoutParams(params);
                                    codeText.setText(getItem(position).getStudent_code());
                                    nameText.setText(getItem(position).getStudent_name());
                                    linearLayout.addView(codeText);
                                    linearLayout.addView(nameText);
                                    return linearLayout;
                                }
                            };
                            selectedStudents.setAdapter(adapter);
                            selectedStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    View v = getActivity().getLayoutInflater().inflate(R.layout.ensureview, null);
                                    final AlertDialog [] dialog = new AlertDialog[1];
                                    Button button = (Button)v.findViewById(R.id.okButton);
                                    button.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            new DeleteEnrollServer(getString(R.string.hyeonWooURL)+"deleteEnroll", classVO.getClass_code(), adapter.getItem(i).getStudent_code(), adapter, adapter.getItem(i), dialog).run();
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
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                                    ListView listView = new ListView(getActivity());
                                    final ThirdPagerAdpater raw = (ThirdPagerAdpater)ThirdFragment.newInstance("").getAdapter();
                                    final ThirdPagerAdpater thirdAdapter = new ThirdPagerAdpater(getActivity());
                                    thirdAdapter.addAll(raw.getList());
                                    for(int i = 0; i<adapter.getCount(); i++){

                                        for(int z = 0; z<thirdAdapter.getCount(); z++){
                                            StudentVO studentVO = thirdAdapter.getItem(z);
                                            if(adapter.getItem(i).getStudent_code().equals(studentVO.getStudent_code())){
                                                thirdAdapter.remove(z);
                                                break;
                                            }
                                        }

                                    }

                                    listView.setAdapter(thirdAdapter);
                                    builder1.setView(listView);
                                    final AlertDialog [] studentDialog = new AlertDialog[1];
                                    studentDialog[0] = builder1.show();
                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            new InsertEnrollServer(getString(R.string.hyeonWooURL)+"insertenroll", thirdAdapter.getItem(i).getStudent_code(),  classVO.getClass_code(),  adapter, thirdAdapter.getItem(i), studentDialog).run();
                                        }
                                    });
                                }
                            });

                            new ClassStudentServer(getString(R.string.hyeonWooURL)+"class", classVO.getClass_code(),adapter).run();
                            builder.setView(view);
                            AlertDialog alertDialog = builder.show();
                        }
                    }
                });
                dialog[0] = builder.show();
            }
        }) ;
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final FourthPagerAdpater adpater = (FourthPagerAdpater)getAdapter();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View v = getActivity().getLayoutInflater().inflate(R.layout.ensureview, null);
                final AlertDialog [] dialog = new AlertDialog[1];
                Button button = (Button)v.findViewById(R.id.okButton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClassVO schoolVO = (ClassVO) adpater.getList().get(i);
                        new DeleteValueServer(getString(R.string.hyeonWooURL)+"deleteData", DeleteValueServer.CLASS, schoolVO.getClass_code(), getAdapter(), dialog).run();
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

    public static FourthFragment newInstance(String text) {
        if(f == null) {
            f = new FourthFragment();
            Bundle b = new Bundle();
            b.putString("msg", text);

            f.setArguments(b);
        }
        return f;
    }
}
package util;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.leejaejun.afterschool.R;

import java.util.ArrayList;

import Server.InsertClassServer;
import Server.InsertSchoolServer;
import Server.InsertStudentServer;
import Server.InsertTeacherServer;
import Server.InsertTextbookServer;
import Server.UpdateClassServer;
import Server.UpdateSchoolServer;
import Server.UpdateStudentServer;
import Server.UpdateTeacherServer;
import Server.UpdateTextbookServer;
import adapter.FifthPagerAdpater;
import adapter.FirstPagerAdpater;
import adapter.SecondPagerAdpater;
import vo.ClassVO;
import vo.SchoolVO;
import vo.StudentVO;
import vo.TeacherVO;
import vo.TextbookVO;

/**
 * Created by LeeJaeJun on 2017-11-09.
 */

public class CreateDialog {
    Activity activity;
    View schoolView, studentView, classView, bookView, teacherView;
    private AlertDialog alertDialog;
    public final static int UPDATE = 0;
    public final static int INSERT= 1;
    Object value;
    public CreateDialog(Activity activity) {
        this.activity = activity;
    }

    public void createView(int index, Object value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(rootView(index));
        alertDialog = builder.show();
        this.value = value; //넣을 값들 -> testBookVO, VO, Vo,
        if(value!=null){
            switch (index){
                case 0:
                    fillSchoolView((SchoolVO)value);
                    break;
                case 1:
                    fillTeacherView((TeacherVO)value);
                    break;
                case 2:
                    fillStudentView((StudentVO)value);
                    break;
                case 3:
                    fillClassView((ClassVO)value);
                    break;
                case 4:
                    fillTextbookView((TextbookVO)value);
                    break;
            }
        }
    }

    private View rootView(int index) {
        View v = activity.getLayoutInflater().inflate(R.layout.create_root, null);
        final Spinner spinner = (Spinner) v.findViewById(R.id.spinner3);
        FrameLayout changeLayout = (FrameLayout) v.findViewById(R.id.changeLayout);
        schoolView = schoolView();
        studentView = studentView();
        classView = classView();
        bookView = bookView();
        teacherView = teacherView();

        changeLayout.addView(schoolView);
        changeLayout.addView(studentView);
        changeLayout.addView(classView);
        changeLayout.addView(bookView);
        changeLayout.addView(teacherView);

        spinner.setSelection(index);

        switchMenu(spinner.getSelectedItemPosition());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switchMenu(spinner.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        viewSetting(v, spinner);
        return v;
    }

    private void viewSetting(final View v, final Spinner spinner) {
        Button confirmButton = (Button) v.findViewById(R.id.button5);
        Button cancelButton = (Button) v.findViewById(R.id.button6);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(value==null){
                        switch (spinner.getSelectedItemPosition()) {
                            case 0:
                                new InsertSchoolServer(activity.getString(R.string.hyeonWooURL) + "insertschool", createSchoolVO()).run();
                                break;
                            case 1:
                                new InsertTeacherServer(activity.getString(R.string.hyeonWooURL)+"insertteacher", createTeacherVO()).run();
                                break;
                            case 2:
                                new InsertStudentServer(activity.getString(R.string.hyeonWooURL)+"insertstudent", createStudentVO()).run();
                                break;
                            case 3:
                                new InsertClassServer(activity.getString(R.string.hyeonWooURL)+"insertclass", createClassVO()).run();
                                break;
                            case 4:
                                new InsertTextbookServer(activity.getString(R.string.hyeonWooURL)+"inserttextbook", createTextbookVO()).run();
                                break;
                        }
                }else{
                    switch(spinner.getSelectedItemPosition()){
                        case 0:
                            syncSchoolView((SchoolVO)value);
                            new UpdateSchoolServer(activity.getString(R.string.hyeonWooURL)+"updateschool", (SchoolVO)value).run();
                            break;
                        case 1:
                            syncTeacherView((TeacherVO)value);
                            new UpdateTeacherServer(activity.getString(R.string.hyeonWooURL)+"updateteacher", (TeacherVO)value).run();
                            break;
                        case 2:
                            syncStudentView((StudentVO)value);
                            new UpdateStudentServer(activity.getString(R.string.hyeonWooURL)+"updatestudent", (StudentVO)value).run();
                            break;
                        case 3:
                            syncClassView((ClassVO)value);
                            new UpdateClassServer(activity.getString(R.string.hyeonWooURL)+"updateclass", (ClassVO)value).run();
                            break;
                        case 4:
                            syncTextbookView((TextbookVO)value);
                            new UpdateTextbookServer(activity.getString(R.string.hyeonWooURL)+"updatetextbook", (TextbookVO)value).run();
                            break;
                    }
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        alertDialog.dismiss();
                    }
                }, 200);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
    }

    private View teacherView() {
        View v = activity.getLayoutInflater().inflate(R.layout.create_teacher, null);
        return v;
    }

    private TeacherVO createTeacherVO() {
        EditText tc_id = (EditText) teacherView.findViewById(R.id.id);
        EditText tc_pw = (EditText) teacherView.findViewById(R.id.pw);
        EditText tc_phone_number = (EditText) teacherView.findViewById(R.id.phone_number);
        EditText tc_email = (EditText) teacherView.findViewById(R.id.email);
        EditText tc_name = (EditText) teacherView.findViewById(R.id.name);
        EditText tc_birth_date = (EditText) teacherView.findViewById(R.id.birth_date);
        return new TeacherVO("", tc_id.getText().toString().trim(), tc_pw.getText().toString().trim(), tc_phone_number.getText().toString().trim(), tc_email.getText().toString().trim(), tc_name.getText().toString().trim(), tc_birth_date.getText().toString().trim());
    }
    private void fillTeacherView(TeacherVO value){
        EditText tc_id = (EditText) teacherView.findViewById(R.id.id);
        EditText tc_pw = (EditText) teacherView.findViewById(R.id.pw);
        EditText tc_phone_number = (EditText) teacherView.findViewById(R.id.phone_number);
        EditText tc_email = (EditText) teacherView.findViewById(R.id.email);
        EditText tc_name = (EditText) teacherView.findViewById(R.id.name);
        EditText tc_birth_date = (EditText) teacherView.findViewById(R.id.birth_date);
        tc_id.setText(value.getTeacher_id());
        tc_pw.setText(value.getTeacher_pw());
        tc_phone_number.setText(value.getTeacher_phone_number());
        tc_email.setText(value.getTeacher_email());
        tc_name.setText(value.getTeacher_name());
        tc_birth_date.setText(value.getTeacher_birth_date());
    }
    private void syncTeacherView(TeacherVO vo) {
        EditText tc_id = (EditText) teacherView.findViewById(R.id.id);
        EditText tc_pw = (EditText) teacherView.findViewById(R.id.pw);
        EditText tc_phone_number = (EditText) teacherView.findViewById(R.id.phone_number);
        EditText tc_email = (EditText) teacherView.findViewById(R.id.email);
        EditText tc_name = (EditText) teacherView.findViewById(R.id.name);
        EditText tc_birth_date = (EditText) teacherView.findViewById(R.id.birth_date);
        vo.setTeacher_birth_date(tc_birth_date.getText().toString().trim());
        vo.setTeacher_name(tc_name.getText().toString().trim());
        vo.setTeacher_id(tc_id.getText().toString().trim());
        vo.setTeacher_pw(tc_pw.getText().toString().trim());
        vo.setTeacher_phone_number(tc_phone_number.getText().toString().trim());
        vo.setTeacher_email(tc_phone_number.getText().toString().trim());
    }

    private View schoolView() {
        View v = activity.getLayoutInflater().inflate(R.layout.create_school, null);
        return v;
    }

    private SchoolVO createSchoolVO() {
        EditText sc_name = schoolView.findViewById(R.id.name);
        EditText sc_address = schoolView.findViewById(R.id.address);
        EditText sc_phone_number = schoolView.findViewById(R.id.phone_number);
        return new SchoolVO("", sc_name.getText().toString().trim(), sc_address.getText().toString().trim(), sc_phone_number.getText().toString().trim());
    }
    private void fillSchoolView(SchoolVO vo){
        EditText sc_name = schoolView.findViewById(R.id.name);
        EditText sc_address = schoolView.findViewById(R.id.address);
        EditText sc_phone_number = schoolView.findViewById(R.id.phone_number);
        sc_name.setText(vo.getSchool_name());
        sc_address.setText(vo.getSchool_address());
        sc_phone_number.setText(vo.getSchool_phone_number());
    }
    private void syncSchoolView(SchoolVO vo){
        EditText sc_name = schoolView.findViewById(R.id.name);
        EditText sc_address = schoolView.findViewById(R.id.address);
        EditText sc_phone_number = schoolView.findViewById(R.id.phone_number);
        vo.setSchool_address(sc_address.getText().toString().trim());
        vo.setSchool_name(sc_name.getText().toString().trim());
        vo.setSchool_phone_number(sc_phone_number.getText().toString().trim());
    }


    private View studentView() {
        View v = activity.getLayoutInflater().inflate(R.layout.create_student, null);
        Spinner sc_spinner = v.findViewById(R.id.spinner);
        FirstPagerAdpater secondPagerAdapter = (FirstPagerAdpater) FirstFragment.newInstance("").getAdapter();
        final ArrayList<SchoolVO> list = secondPagerAdapter.getList();
        sc_spinner.setAdapter(new StudentSpinnerAdpater(list));
        return v;
    }

    private StudentVO createStudentVO() {
        EditText st_name = (EditText) studentView.findViewById(R.id.name);
        String name = st_name.getText().toString().trim();
        EditText st_birth_date = (EditText) studentView.findViewById(R.id.birth_date);
        String birth = st_birth_date.getText().toString().trim();
        Spinner sc_spinner = (Spinner) studentView.findViewById(R.id.spinner);
        EditText sc_grade = (EditText) studentView.findViewById(R.id.sc_grade);
        int grade = Integer.parseInt(sc_grade.getText().toString().trim());
        EditText sc_class = (EditText) studentView.findViewById(R.id.sc_class);
        int class_ = Integer.parseInt(sc_class.getText().toString().trim());
        EditText st_phone_number = (EditText) studentView.findViewById(R.id.st_phone_number);
        String phone = st_phone_number.getText().toString().trim();
        EditText st_pt_phone_number = (EditText) studentView.findViewById(R.id.pt_phone_number);
        String mother_phone = st_pt_phone_number.getText().toString().trim();
        CheckBox st_vpstate = (CheckBox) studentView.findViewById(R.id.checkBox);
        int voucher = st_vpstate.isChecked()?1:0;
        StudentSpinnerAdpater adpater = (StudentSpinnerAdpater)sc_spinner.getAdapter();
        SchoolVO schoolVO = (SchoolVO)adpater.getItem(sc_spinner.getSelectedItemPosition());
        return new StudentVO("", name, schoolVO.getSchool_code(), grade, class_, birth, phone, mother_phone, voucher);
    }
    private void fillStudentView(StudentVO vo){
        EditText st_name = (EditText) studentView.findViewById(R.id.name);
        EditText st_birth_date = (EditText) studentView.findViewById(R.id.birth_date);
        Spinner sc_spinner = (Spinner) studentView.findViewById(R.id.spinner);
        EditText sc_grade = (EditText) studentView.findViewById(R.id.sc_grade);
        EditText sc_class = (EditText) studentView.findViewById(R.id.sc_class);
        EditText st_phone_number = (EditText) studentView.findViewById(R.id.st_phone_number);
        EditText st_pt_phone_number = (EditText) studentView.findViewById(R.id.pt_phone_number);
        CheckBox st_vpstate = (CheckBox) studentView.findViewById(R.id.checkBox);
        st_name.setText(vo.getStudent_name());
        st_birth_date.setText(vo.getStudent_birth_date());
        sc_grade.setText(String.valueOf(vo.getSchool_grade()));
        sc_class.setText(String.valueOf(vo.getSchool_class()));
        st_phone_number.setText(vo.getStudent_phone_number());
        st_pt_phone_number.setText(vo.getProtector_phone_number());
        st_vpstate.setChecked(vo.isVoucher_program_state());
        StudentSpinnerAdpater fir = (StudentSpinnerAdpater)sc_spinner.getAdapter();
        int index = fir.indexOf(vo.getSchool_code());
        sc_spinner.setSelection(index);
    }
    private void syncStudentView(StudentVO vo){
        EditText st_name = (EditText) studentView.findViewById(R.id.name);
        EditText st_birth_date = (EditText) studentView.findViewById(R.id.birth_date);
        Spinner sc_spinner = (Spinner) studentView.findViewById(R.id.spinner);
        EditText sc_grade = (EditText) studentView.findViewById(R.id.sc_grade);
        EditText sc_class = (EditText) studentView.findViewById(R.id.sc_class);
        EditText st_phone_number = (EditText) studentView.findViewById(R.id.st_phone_number);
        EditText st_pt_phone_number = (EditText) studentView.findViewById(R.id.pt_phone_number);
        CheckBox st_vpstate = (CheckBox) studentView.findViewById(R.id.checkBox);

        vo.setProtector_phone_number(st_pt_phone_number.getText().toString().trim());
        vo.setSchool_class(Integer.parseInt(sc_class.getText().toString().trim()));
        vo.setSchool_grade(Integer.parseInt(sc_grade.getText().toString().trim()));
        SchoolVO schoolVO = (SchoolVO)sc_spinner.getSelectedItem();
        vo.setSchool_code(schoolVO.getSchool_code());
        vo.setStudent_name(st_name.getText().toString().trim());
        vo.setStudent_birth_date(st_birth_date.getText().toString().trim());
        vo.setStudent_phone_number(st_phone_number.getText().toString().trim());
        vo.setVoucher_program_state(st_vpstate.isChecked());
    }

    private View classView() {
        View v = activity.getLayoutInflater().inflate(R.layout.create_class, null);
        Spinner c_spinner = (Spinner) v.findViewById(R.id.spinner2);
        FifthPagerAdpater fifthPagerAdapter = (FifthPagerAdpater) FifthFragment.newInstance("").getAdapter();
        final ArrayList<TextbookVO> list = fifthPagerAdapter.getList();
        c_spinner.setAdapter(new ClassTextSpinnerAdpater(list));
        Spinner t_spinner = v.findViewById(R.id.spinner3);
        SecondPagerAdpater secondPagerAdpater = (SecondPagerAdpater)SecondFragment.newInstance("").getAdapter();
        final ArrayList<TeacherVO> list1 = secondPagerAdpater.getList();
        t_spinner.setAdapter(new ClassTeacherSpinnerAdpater(list1));
        Spinner school_spinner = v.findViewById(R.id.spinner4);
        FirstPagerAdpater firstPagerAdpater = (FirstPagerAdpater)FirstFragment.newInstance("").getAdapter();
        final ArrayList<SchoolVO> list2 = firstPagerAdpater.getList();
        school_spinner.setAdapter(new ClassSchoolSpinnerAdpater(list2));
        return v;
    }

    private ClassVO createClassVO() {
        EditText c_name = (EditText) classView.findViewById(R.id.name);
        String name = c_name.getText().toString().trim();
        EditText c_start_date = (EditText) classView.findViewById(R.id.start_date);
        String start = c_start_date.getText().toString().trim();
        EditText c_finish_date = (EditText) classView.findViewById(R.id.finish_date);
        String finish = c_finish_date.getText().toString().trim();
        EditText c_tuition = (EditText) classView.findViewById(R.id.tuition);
        int tuition = Integer.parseInt(c_tuition.getText().toString().trim());
        Spinner c_spinner = (Spinner) classView.findViewById(R.id.spinner2);
        Spinner t_spinner = (Spinner) classView.findViewById(R.id.spinner3);
        Spinner daySpinner = (Spinner) classView.findViewById(R.id.spinner5);
        int day = daySpinner.getSelectedItemPosition()+1;
        Spinner timeSpinner = (Spinner) classView.findViewById(R.id.spinner6);
        int time = timeSpinner.getSelectedItemPosition()+9;
        Spinner school_spinner = classView.findViewById(R.id.spinner4);
        ClassTextSpinnerAdpater textAdapter = (ClassTextSpinnerAdpater)c_spinner.getAdapter();
        ClassTeacherSpinnerAdpater teacherAdapter = (ClassTeacherSpinnerAdpater)t_spinner.getAdapter();
        ClassSchoolSpinnerAdpater schoolAdapter = (ClassSchoolSpinnerAdpater) school_spinner.getAdapter();
        TextbookVO textbookVO = (TextbookVO)textAdapter.getItem(c_spinner.getSelectedItemPosition());
        TeacherVO teacherVO = (TeacherVO)teacherAdapter.getItem(t_spinner.getSelectedItemPosition());
        SchoolVO schoolVO = (SchoolVO)schoolAdapter.getItem(school_spinner.getSelectedItemPosition());
        return new ClassVO("", teacherVO.getTeacher_code(), name, schoolVO.getSchool_code(),  day, time, textbookVO.getTextbook_code(), start, finish, tuition);
    }
    private void fillClassView(ClassVO classVO){
        EditText c_name = (EditText) classView.findViewById(R.id.name);
        EditText c_start_date = (EditText) classView.findViewById(R.id.start_date);
        EditText c_finish_date = (EditText) classView.findViewById(R.id.finish_date);
        EditText c_tuition = (EditText) classView.findViewById(R.id.tuition);
        Spinner c_spinner = (Spinner) classView.findViewById(R.id.spinner2); //교재명
        Spinner t_spinner = (Spinner) classView.findViewById(R.id.spinner3); //강사
        Spinner daySpinner = (Spinner) classView.findViewById(R.id.spinner5);
        Spinner timeSpinner = (Spinner) classView.findViewById(R.id.spinner6);
        Spinner school_spinner = classView.findViewById(R.id.spinner4);
        c_name.setText(classVO.getClass_name());
        c_start_date.setText(classVO.getStart_date());
        c_finish_date.setText(classVO.getFinish_date());
        c_tuition.setText(String.valueOf(classVO.getTuition()));
        ClassTextSpinnerAdpater  classTextSpinnerAdpater = (ClassTextSpinnerAdpater)c_spinner.getAdapter();
        int textBookIndex = classTextSpinnerAdpater.indexOf(classVO.getTextbook_code());
        c_spinner.setSelection(textBookIndex);
        ClassTeacherSpinnerAdpater classTeacherSpinnerAdpater = (ClassTeacherSpinnerAdpater)t_spinner.getAdapter();
        int teacherIndex = classTeacherSpinnerAdpater.indexOf(classVO.getTeacher_code());
        t_spinner.setSelection(teacherIndex);
        ClassSchoolSpinnerAdpater classSchoolSpinnerAdpater = (ClassSchoolSpinnerAdpater)school_spinner.getAdapter();
        int schoolIndex = classSchoolSpinnerAdpater.indexOf(classVO.getSchool_code());
        school_spinner.setSelection(schoolIndex);
        daySpinner.setSelection(classVO.getClass_date()-1);
        timeSpinner.setSelection(classVO.getClass_time()-9);
    }
    private void syncClassView(ClassVO vo){
        EditText c_name = (EditText) classView.findViewById(R.id.name);
        String name = c_name.getText().toString().trim();
        EditText c_start_date = (EditText) classView.findViewById(R.id.start_date);
        String start = c_start_date.getText().toString().trim();
        EditText c_finish_date = (EditText) classView.findViewById(R.id.finish_date);
        String finish = c_finish_date.getText().toString().trim();
        EditText c_tuition = (EditText) classView.findViewById(R.id.tuition);
        int tuition = Integer.parseInt(c_tuition.getText().toString().trim());
        Spinner c_spinner = (Spinner) classView.findViewById(R.id.spinner2);
        Spinner t_spinner = (Spinner) classView.findViewById(R.id.spinner3);
        Spinner daySpinner = (Spinner) classView.findViewById(R.id.spinner5);
        int day = daySpinner.getSelectedItemPosition()+1;
        Spinner timeSpinner = (Spinner) classView.findViewById(R.id.spinner6);
        int time = timeSpinner.getSelectedItemPosition()+9;
        Spinner school_spinner = classView.findViewById(R.id.spinner4);
        vo.setClass_name(name);
        vo.setStart_date(start);
        vo.setFinish_date(finish);
        vo.setTuition(tuition);
        vo.setClass_date(day);
        vo.setClass_time(time);
        ClassTextSpinnerAdpater  classTextSpinnerAdpater = (ClassTextSpinnerAdpater)c_spinner.getAdapter();
        vo.setTextbook_code(classTextSpinnerAdpater.getItem(c_spinner.getSelectedItemPosition()).getTextbook_code());
        ClassTeacherSpinnerAdpater classTeacherSpinnerAdpater = (ClassTeacherSpinnerAdpater)t_spinner.getAdapter();
        vo.setTeacher_code(classTeacherSpinnerAdpater.getItem(t_spinner.getSelectedItemPosition()).getTeacher_code());
        ClassSchoolSpinnerAdpater classSchoolSpinnerAdpater = (ClassSchoolSpinnerAdpater)school_spinner.getAdapter();
        vo.setSchool_code(classSchoolSpinnerAdpater.getItem(school_spinner.getSelectedItemPosition()).getSchool_code());
    }

    private View bookView() {
        View v = activity.getLayoutInflater().inflate(R.layout.create_book, null);
        return v;
    }

    private TextbookVO createTextbookVO() {
        EditText tb_name = (EditText) bookView.findViewById(R.id.name);
        EditText tb_price = (EditText) bookView.findViewById(R.id.price);
        int price = Integer.parseInt(tb_price.getText().toString().trim());
        return new TextbookVO("", tb_name.getText().toString().trim(), price);
    }
    private void fillTextbookView(TextbookVO vo){
        EditText tb_name = (EditText) bookView.findViewById(R.id.name);
        EditText tb_price = (EditText) bookView.findViewById(R.id.price);
        tb_name.setText(vo.getTextbook_name());
        tb_price.setText(String.valueOf(vo.getTextbook_price()));
    }
    private void syncTextbookView(TextbookVO vo){
        EditText tb_name = (EditText) bookView.findViewById(R.id.name);
        EditText tb_price = (EditText) bookView.findViewById(R.id.price);
        vo.setTextbook_name(tb_name.getText().toString().trim());
        vo.setTextbook_price(Integer.parseInt(tb_price.getText().toString().trim()));
    }

    private void switchMenu(int position) {
        schoolView.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        teacherView.setVisibility(position == 1 ? View.VISIBLE : View.GONE);
        studentView.setVisibility(position == 2 ? View.VISIBLE : View.GONE);
        classView.setVisibility(position == 3 ? View.VISIBLE : View.GONE);
        bookView.setVisibility(position == 4 ? View.VISIBLE : View.GONE);
    }

    class StudentSpinnerAdpater extends BaseAdapter {
        ArrayList<SchoolVO> list;

        public StudentSpinnerAdpater(ArrayList<SchoolVO> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public SchoolVO getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(activity);
            SchoolVO schoolVO = (SchoolVO) getItem(i);
            textView.setText(schoolVO.getSchool_name());
            return textView;
        }
        public int indexOf(String school_code){
            int index;
            for(index = 0; index<list.size(); index++){
                if(list.get(index).getSchool_code().equals(school_code))
                    return index;
            }
            return -1;
        }
    }
    class ClassTeacherSpinnerAdpater extends BaseAdapter {
        ArrayList<TeacherVO> list;

        public ClassTeacherSpinnerAdpater(ArrayList<TeacherVO> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public TeacherVO getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(activity);;
            TeacherVO teacherVO = (TeacherVO) getItem(i);
            textView.setText(teacherVO.getTeacher_name());
            return textView;
        }
        public int indexOf(String teacher_code){
            int index;
            for(index = 0; index<list.size(); index++){
                if(list.get(index).getTeacher_code().equals(teacher_code))
                    return index;
            }
            return -1;
        }
    }
    class ClassTextSpinnerAdpater extends BaseAdapter {
        ArrayList<TextbookVO> list;

        public ClassTextSpinnerAdpater(ArrayList<TextbookVO> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public TextbookVO getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(activity);;
            TextbookVO textbookVO = (TextbookVO) getItem(i);
            textView.setText(textbookVO.getTextbook_name());
            return textView;
        }
        public int indexOf(String textBook_code){
            int index;
            for(index = 0; index<list.size(); index++){
                if(list.get(index).getTextbook_code().equals(textBook_code))
                    return index;
            }
            return -1;
        }
    }

    class ClassSchoolSpinnerAdpater extends BaseAdapter {
        ArrayList<SchoolVO> list;

        public ClassSchoolSpinnerAdpater(ArrayList<SchoolVO> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public SchoolVO getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(activity);;
            SchoolVO schoolVO = (SchoolVO) getItem(i);
            textView.setText(schoolVO.getSchool_name());
            return textView;
        }
        public int indexOf(String school_code){
            int index;
            for(index = 0; index<list.size(); index++){
                if(list.get(index).getSchool_code().equals(school_code))
                    return index;
            }
            return -1;
        }
    }
}

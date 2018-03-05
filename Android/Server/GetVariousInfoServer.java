package Server;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import adapter.FifthPagerAdpater;
import adapter.FirstPagerAdpater;
import adapter.FourthPagerAdpater;
import adapter.SecondPagerAdpater;
import adapter.ThirdPagerAdpater;
import vo.ClassVO;
import vo.SchoolVO;
import vo.StudentVO;
import vo.TeacherVO;
import vo.TextbookVO;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

public class GetVariousInfoServer extends ServerRootHyoen {
    public static final int SCHOOL = 0;
    public static final int STUDENT = 1;
    public static final int CLASS = 2;
    public static final int TEXTBOOK = 3;
    public static final int TEACHER = 4;
    private int myType;
    private String value = null;
    private BaseAdapter baseAdapter;

    public GetVariousInfoServer(String URL, BaseAdapter baseAdapter, int type) {
        super(URL);
        myType = type;
        this.baseAdapter = baseAdapter;
        switch (type) {
            case SCHOOL:
                value = "school";
                break;
            case STUDENT:
                value = "student";
                break;
            case CLASS:
                value = "class";
                break;
            case TEXTBOOK:
                value = "textbook";
                break;
            case TEACHER:
                value = "teacher";
                break;
        }
    }

    @Override
    protected void afterResponse(String s) {
        Log.d("결과값 : ", s);
        Gson gson = new Gson();
        switch (myType) {
            case SCHOOL: {
                SchoolVO[] list = gson.fromJson(s, SchoolVO[].class);
                FirstPagerAdpater fixAdapter = (FirstPagerAdpater) baseAdapter;
                fixAdapter.addAll(list);
            }
            break;
            case TEACHER: {
                TeacherVO[] list = gson.fromJson(s, TeacherVO[].class);
                SecondPagerAdpater fixAdapter = (SecondPagerAdpater) baseAdapter;
                fixAdapter.addAll(list);
            }
            break;
            case STUDENT: {
                StudentVO[] list = gson.fromJson(s, StudentVO[].class);
                ThirdPagerAdpater fixAdapter = (ThirdPagerAdpater) baseAdapter;
                fixAdapter.addAll(list);
            }
            break;
            case CLASS: {
                ClassVO[] list = gson.fromJson(s, ClassVO[].class);
                for(int i = 0; i<list.length; i++)
                    list[i].plusOneForGson();
                FourthPagerAdpater fixAdapter = (FourthPagerAdpater) baseAdapter;
                fixAdapter.addAll(list);
            }
                break;
            case TEXTBOOK: {
                TextbookVO[] list = gson.fromJson(s, TextbookVO[].class);
                FifthPagerAdpater fixAdapter = (FifthPagerAdpater) baseAdapter;
                fixAdapter.addAll(list);
            }
                break;
        }

    }

    @Override
    protected void setParameter() {
        map.put("necessary_data", value);
    }
}

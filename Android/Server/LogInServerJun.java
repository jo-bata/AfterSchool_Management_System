package Server;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.leejaejun.afterschool.MainActivity;
import com.example.leejaejun.afterschool.R;
import com.google.gson.Gson;

import java.util.HashMap;

import vo.TeacherVO;

import static android.app.Activity.RESULT_OK;

/**
 * Created by LeeJaeJun on 2017-11-28.
 */

public class LogInServerJun extends ServerRootJun {
    private String id;
    private String ps;
    private Activity activity;
    public LogInServerJun(Activity activity, String URL, String id, String ps) {
        super(URL);
        this.activity = activity;
        this.id = id;
        this.ps = ps;
    }

    @Override
    public void afterResponse(String s) {
        Gson gson = new Gson();
        HashMap<String, Object> hashMap = gson.fromJson(s.toString(),HashMap.class);
        if(hashMap.get("login").equals("missid")){
            Toast.makeText(activity, "비밀번호 오류", Toast.LENGTH_SHORT).show();
        }else if(hashMap.get("login").equals("misspw")){
            Toast.makeText(activity, "비밀번호 오류", Toast.LENGTH_SHORT).show();
        }else if(hashMap.get("login").equals("login")){
            Toast.makeText(activity, "로그인 완료", Toast.LENGTH_SHORT).show();
            MainActivity.me = gson.fromJson(String.valueOf(hashMap.get("teacher")), TeacherVO.class);

            new AuthorityCheckServer(activity, activity.getString(R.string.hyeonWooURL)+"admin").run();
        }
    }

    @Override
    public void setParameter() {
        map.put("id", id);
        map.put("pw", ps);
    }
}

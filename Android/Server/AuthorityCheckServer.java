package Server;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.leejaejun.afterschool.MainActivity;
import com.example.leejaejun.afterschool.UploadActivity;
import com.google.gson.Gson;

import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

/**
 * Created by LeeJaeJun on 2017-11-28.
 */

public class AuthorityCheckServer extends ServerRootHyoen {
    Activity activity;
    public AuthorityCheckServer(Activity activity, String URL) {
        super(URL);
        this.activity = activity;
    }

    @Override
    protected void afterResponse(String s) {
        Gson gson = new Gson();
        HashMap<String, Boolean> resultMap = gson.fromJson(s, HashMap.class);
        boolean result = resultMap.get("authority");
        if(result){
            Intent intent = new Intent(activity, UploadActivity.class);
            activity.startActivity(intent);
        }else{
            Intent intent = new Intent(activity, MainActivity.class);
            activity.setResult(RESULT_OK, intent);
            activity.finish();
        }
    }

    @Override
    protected void setParameter() {
        map.put("teacher_code", MainActivity.me.getTeacher_code());
    }

}

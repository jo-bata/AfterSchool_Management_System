package Server;

import com.example.leejaejun.afterschool.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.CalendarBaseAdapter;
import vo.CalendarVO;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

public class TimeTableServer extends ServerRootJun{
    CalendarBaseAdapter adapter;
    public TimeTableServer(String URL, CalendarBaseAdapter adapter) {
        super(URL);
        this.adapter = adapter;
    }

    @Override
    protected void afterResponse(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("timetable");
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject o = jsonArray.getJSONObject(i);
                CalendarVO calendarVO = new CalendarVO(o.getString("sn"), o.getString("cn"));
                adapter.addSchedule(o.getInt("cd"), o.getInt("ct"), calendarVO);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setParameter() {
        map.put("t_code", MainActivity.me.getTeacher_code());
    }
}

package Server;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by LeeJaeJun on 2017-11-28.
 */

public abstract class ServerRootJun extends AsyncTask<String, String, String> {
    private String URL;
    protected HashMap<String, String> map;
    private final OkHttpClient client = new OkHttpClient();

    public ServerRootJun(String URL) {
        this.URL = URL;
    }
    @Override
    protected void onPreExecute() {
        map = new HashMap<String, String>();
        setParameter();
    }

    @Override
    protected String doInBackground(String... strings) {
        Response response;
        RequestBody requestBody = null;
        try {
            FormBody.Builder builder = new FormBody.Builder();
            for(String key : map.keySet())
                builder.add(key, URLEncoder.encode(map.get(key),"utf-8"));
            requestBody = builder.build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Request request = new Request.Builder().
                url(URL).
                post(requestBody).
                build();
        Log.d("URL ::::", URL);
        try {
            response = client.newCall(request).execute();
            String answer = response.body().string();
            return answer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            afterResponse(URLDecoder.decode(s, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void run(){
        execute("");
    }

    protected abstract void afterResponse(String s);
    protected abstract void setParameter();
}

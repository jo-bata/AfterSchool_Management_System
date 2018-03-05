package Server;

/**
 * Created by LeeJaeJun on 2017-11-29.
 */

public class AttendanceServer extends ServerRootJun{

    private int c_code;
    public AttendanceServer(String URL, int c_code) {
        super(URL);
        this.c_code = c_code;
    }

    @Override
    protected void afterResponse(String s) {

    }

    @Override
    protected void setParameter() {
        map.put("c_code", String.valueOf(c_code));
    }
}

package cn.edu.hebiace.javajpk.api;

import cn.edu.hebiace.javajpk.api.net.HttpUtil;

/**
 * Created by duke on 15-12-22.
 */
public class ApiImpl implements Api {

    private static final String TAG = "ApiImpl";
    private HttpUtil httpUtil;

    public ApiImpl() {
        httpUtil = HttpUtil.getInstance();
    }

    @Override
    public ApiResponse<Void> login(String user_name, String user_pwd) {
        return null;
    }

    @Override
    public ApiResponse<Void> register(String user_name, String user_pwd) {
        return null;
    }
}

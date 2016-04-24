package cn.edu.hebiace.javajpk.core;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import cn.edu.hebiace.javajpk.api.Api;
import cn.edu.hebiace.javajpk.api.ApiImpl;
import cn.edu.hebiace.javajpk.api.ApiResponse;

/**
 * Created by duke on 15-12-22.
 */
public class AppActionImpl implements AppAction {

    private static final String TAG = "AppActionImpl";
    private Context context;
    private Api api;

    public AppActionImpl(Context context) {
        this.context = context;
        this.api = new ApiImpl();
    }

    @Override
    public void login(String user_name, String user_pwd, ActionCallBackListener<Void> callBackListener) {

    }

    @Override
    public void register(String user_name, String user_pwd, ActionCallBackListener<Void> callBackListener) {

    }
}

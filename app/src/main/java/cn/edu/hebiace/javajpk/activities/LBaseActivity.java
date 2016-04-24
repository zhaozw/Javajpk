package cn.edu.hebiace.javajpk.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import cn.edu.hebiace.javajpk.LApplication;
import cn.edu.hebiace.javajpk.core.AppAction;

/**
 * Created by lpwxs on 15-12-3.
 */
public class LBaseActivity extends FragmentActivity {

    // 上下文实例
    public Context context;
    // 应用全局的实例
    public LApplication application;
    // 核心层的Action实例
    public AppAction appAction;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        application = (LApplication) this.getApplication();
        appAction = application.getAppAction();

    }
}

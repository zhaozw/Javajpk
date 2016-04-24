package cn.edu.hebiace.javajpk;

import android.app.Application;


import cn.edu.hebiace.javajpk.core.AppAction;
import cn.edu.hebiace.javajpk.core.AppActionImpl;

/**
 * Created by duke on 15-12-3.
 */
public class LApplication extends Application {
    private static final String TAG = "LApplication";
    public AppAction appAction;

    @Override
    public void onCreate() {
        super.onCreate();
        appAction = new AppActionImpl(this);
    }


    public AppAction getAppAction() {
        return appAction;
    }

}

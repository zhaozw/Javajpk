package cn.edu.hebiace.javajpk.core;

import java.util.List;

/**
 * Created by duke on 15-12-22.
 */
public interface AppAction {

    /**
     * user login
     *
     * @param user_name
     * @param user_pwd
     * @param callBackListener
     */
    void login(String user_name, String user_pwd, ActionCallBackListener<Void> callBackListener);
    /**
     * user register
     * @param user_name
     * @param user_pwd
     * @param callBackListener
     */
    void register(String user_name, String user_pwd, ActionCallBackListener<Void> callBackListener);


}

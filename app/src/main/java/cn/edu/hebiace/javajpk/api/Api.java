package cn.edu.hebiace.javajpk.api;

/**
 * Created by duke on 15-12-22.
 */
public interface Api {

    /**
     * user login
     *
     * @param user_name
     * @param user_pwd
     * @return
     */
    ApiResponse<Void> login(String user_name, String user_pwd);

    /**
     * user register
     *
     * @param user_name
     * @param user_pwd
     * @return
     */
    ApiResponse<Void> register(String user_name, String user_pwd);

}

package cn.edu.hebiace.javajpk.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lpwxs on 15-12-5.
 */
public class UserUtils {

    private SharedPreferences sprefs;
    private SharedPreferences.Editor editor;
    private static UserUtils userUtils = null;

    private UserUtils(Context context) {
        sprefs = context.getSharedPreferences("user", Context.MODE_APPEND);
    }

    public static UserUtils getUserUtils(Context ctx) {
        if (userUtils == null) {
            userUtils = new UserUtils(ctx);
        }
        return userUtils;
    }

    public boolean setNetType(int type){
        editor = sprefs.edit();
        editor.putInt("nettype",type);
        return editor.commit();
    }
    public int getNetType(){
        return sprefs.getInt("nettype",0);
    }

    public boolean setIsFirst(boolean isFirst){
        editor = sprefs.edit();
        editor.putBoolean("firstlogin",isFirst);
        return editor.commit();
    }
    public boolean isFirst(){
        return sprefs.getBoolean("firstlogin",true);
    }

    public boolean setUserName(String name) {
        editor = sprefs.edit();
        editor.putString("name", name);
        return editor.commit();
    }

    public String getUserName() {
        return sprefs.getString("name", "");
    }

    public boolean setUserId(int id) {
        editor = sprefs.edit();
        editor.putInt("id", id);
        return editor.commit();
    }

    /**
     * 如果读取ID失败，返回-1
     *
     * @return UserId
     */
    public int getUserId() {
        return sprefs.getInt("id", -1);
    }

    public boolean setCityName(String cityName) {
        editor = sprefs.edit();
        editor.putString("city", cityName);
        return editor.commit();
    }

    public String getCity() {
        return sprefs.getString("city", "");
    }


    public boolean setCityAddress(String address) {
        editor = sprefs.edit();
        editor.putString("cityaddress", address);
        return editor.commit();
    }

    public String getCityAddress() {
        return sprefs.getString("cityaddress", "");
    }
}

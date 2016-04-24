package cn.edu.hebiace.javajpk.utils;

/**
 * Created by duke on 16-3-15.
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        if (str != null && str.equals("")) {
            return true;
        }
        return false;
    }
}

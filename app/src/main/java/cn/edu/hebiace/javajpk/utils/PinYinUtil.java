package cn.edu.hebiace.javajpk.utils;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * Created by Duke on 2015/12/18.
 */
public class PinYinUtil {
    /**
     * 将汉字转成拼音
     *
     * @param name
     * @return
     */
    public static String getPinyin(String name) {
        char[] hanzi = name.trim().toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char h : hanzi) {
            String[] p = PinyinHelper.toHanyuPinyinStringArray(h);
            for (String s : p) {
                sb.append(s.substring(0, s.length() - 1));
            }
        }
        return sb.toString();
    }

}

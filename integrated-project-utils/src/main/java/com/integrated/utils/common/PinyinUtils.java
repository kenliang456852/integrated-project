package com.integrated.utils.common;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * ClassName: PinyinUtils
 * Description:
 * Author: liangchao
 * Date: 2018/5/8 17:59
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class PinyinUtils {
    public static String getQuanPin(String str) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < str.length(); ++i) {
            char s = str.charAt(i);
            String[] s1 = PinyinHelper.toHanyuPinyinStringArray(s);
            if (null == s1) {
                sb.append(s);
            } else {
                String up = s1[0].substring(0, s1[0].length() - 1);
                up = up.toUpperCase();
                sb.append(up);
            }
        }

        return sb.toString();
    }
}

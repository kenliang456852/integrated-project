package com.integrated.utils.common;

import java.net.URLDecoder;

/**
 * ClassName: StringCommonUtils
 * Description:
 * Author: liangchao
 * Date: 2018/4/16 21:50
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class StringCommonUtils {
    /**
     * @Description 过滤null字符串变成""
     * @author liangchao
     * @date 2018/4/25 19:08
     * @param value
     * @return
     */
    private String getStringFilterNull(String value) {
        if(null == value || "".equals(value)) {
            return "";
        }
        return value.trim();
    }

    /**
     * @Description 字符串首字母大写
     * @author liangchao
     * @date 2018/4/25 19:09
     * @param value
     * @return
     */
    public static String captureName(String value) {
        //     name = name.substring(0, 1).toUpperCase() + name.substring(1);
        //        return  name;
        char[] cs = value.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);

    }

    /**
     * @Description 对特殊字符进行转译UTF-8
     * @author liangchao
     * @date 2018/4/26 16:48
     * @param value
     * @return
     */
    public static String decodeUrl(String value) {
        String decodeUrl = "";
        try {
            String transformUrl = value.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            decodeUrl = URLDecoder.decode(transformUrl,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return decodeUrl;
        }
    }
}

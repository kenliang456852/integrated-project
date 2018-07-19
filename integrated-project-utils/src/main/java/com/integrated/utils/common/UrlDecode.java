package com.integrated.utils.common;

import java.net.URLDecoder;

/**
 * ClassName: urldecode
 * Description:
 * Author: liangchao
 * Date: 2018/4/20 19:43
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class UrlDecode {

    /**
     * @Description  设置Url编码问题
     * @author liangchao
     * @date 2018/4/20 19:45
     * @param url
     * @参考网址：https://blog.csdn.net/vgwciro8nu/article/details/60117569
     * @return
     */
    public static String decodeUrl(String url) {
        String decodeUrl = "";
        try {
            String transformUrl = url.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            decodeUrl = URLDecoder.decode(transformUrl,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return decodeUrl;
        }
    }
}

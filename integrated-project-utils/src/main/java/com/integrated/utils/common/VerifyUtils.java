package com.integrated.utils.common;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * ClassName: VerifyUtils
 * Description:
 * Author: liangchao
 * Date: 2018/5/11 17:53
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class VerifyUtils {

    /**
     * @Description 数字正则
     * @author liangchao
     * @date 2018/5/12 15:12
     */
    private static final String NUMBER_REGEX = "^[0-9]*$";

    /**
     * @Description 手机号正则
     * @author liangchao
     * @date 2018/5/12 15:13
     */
    private static final String MOBILE_PHONE_REGEX = "^((13[0-9])|(14[0-9])|(15([0-9]))|(17[0-9])|(18[0-9]))\\d{8}$";

    /**
     * @Description email正则
     * @author liangchao
     * @date 2018/5/12 15:13
     */
    private static final String EMAIL_REGEX = "^[A-Za-z0-9\\u4e00-\\u9fa5_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    /**
     * @Description uri正则
     * @author liangchao
     * @date 2018/5/12 15:12
     */
    private static final String URI_REGEX = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";

    /**
     * @Description 第一代身份证正则表达式(15位)
     * @author liangchao
     * @date 2018/5/12 15:21
     */
    private static final String IDCARD15_REGEX = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";

    /**
     * @Description 第二代身份证正则表达式(18位)
     * @author liangchao
     * @date 2018/5/12 15:21
     */
    private static final String IDCARD18_REGEX ="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$";

    /**
     * @Description 校验email
     * @author liangchao
     * @date 2018/5/11 18:04
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if(StringUtils.isNotBlank(email)
                && isPattern(EMAIL_REGEX, email)) {
            return true;
        }
        return false;
    }

    /**
     * @Description 校验手机号
     * @author liangchao
     * @date 2018/5/11 18:28
     * @param mobilePhone
     * @return
     */
    public static boolean isMobilePhone(String mobilePhone) {
        if(StringUtils.isNotBlank(mobilePhone)
                && isPattern(MOBILE_PHONE_REGEX, mobilePhone)) {
            return true;
        }
        return false;
    }

    /**
     * @Description 校验uri格式是否正确
     * @author liangchao
     * @date 2018/5/12 12:42
     * @param uri
     * @return
     */
    public static boolean isUri(String uri) {
        if(StringUtils.isNotBlank(uri)
                && isPattern(URI_REGEX, uri)) {
            return true;
        }
        return false;
    }

    /**
     * @Description 校验uri是否能调通 注意没参数。
     * @author liangchao
     * @date 2018/5/12 13:09
     * @param uri
     * @param method 设置URL请求的访问方式，GET POST HEAD OPTIONS PUT DELETE TRACE
     *                以上方法之一是合法的，具体取决于协议的限制。
     * @return
     */
    public static boolean isConnectionUri(String uri, String method){
        try {
            HttpURLConnection.setFollowRedirects(false);
            // URL所引用的远程对象的连接
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) new URL(uri).openConnection();
            //设置URL请求的访问方式
            conn.setRequestMethod(method);
            // 从HTTP响应消息获取状态码
            return (conn.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (IOException e) {
        }

        return false;
    }

    /**
     * @Description 校验身份证
     * @author liangchao
     * @date 2018/5/12 18:03
     * @param idCard
     * @return
     */
    public static boolean isIdCard(String idCard) {
        if(StringUtils.isNotBlank(idCard)
                && (isPattern(IDCARD15_REGEX, idCard)
                    || isPattern(IDCARD18_REGEX, idCard)))
            return true;
        return false;
    }

    public static boolean isPattern(String reg, String value) {
        try {
            return Pattern.compile(reg).matcher(value).matches();
        } catch (Exception e) {
        }
        return false;
    }

}

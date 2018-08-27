package com.integrated.utils.common;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: StringUtils
 * Description: 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * Author: liangchao
 * Date: 2018/4/28 10:40
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    public static final String FOLDER_DELIMITER = "/";
    public static final char SEPARATOR = '_';
    public static final String CHARSET_NAME = "UTF-8";

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
     * @参考网址：https://blog.csdn.net/vgwciro8nu/article/details/60117569
     * @return
     */
    public static String decodeUrl(String value) {
        String decodeUrl = "";
        try {
            String transformUrl = value.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            decodeUrl = URLDecoder.decode(transformUrl, CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return decodeUrl;
        }
    }

    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 转换为字节数组
     *
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }


    /**
     * 是否包含字符串
     *
     * @param str
     *            验证字符串
     * @param strs
     *            字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }


    /**
     * 替换为手机识别的HTML，去掉样式及属性，保留回车。
     *
     * @param html
     * @return
     */
    public static String replaceMobileHtml(String html) {
        if (html == null) {
            return "";
        }
        return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
    }


    /**
     * 替换为手机识别的HTML，去掉样式及属性，保留回车。
     *
     * @param txt
     * @return
     */
//    public static String toHtml(String txt) {
//        if (txt == null) {
//            return "";
//        }
//        return replace(replace(Encodes.escapeHtml(txt), "\n", "<br/>"), "\t", "&nbsp; &nbsp; ");
//    }


    /**
     * 缩略字符串（不区分中英文字符）
     *
     * @param str
     *            目标字符串
     * @param length
     *            截取长度
     * @return
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String abbr2(String param, int length) {
        if (param == null) {
            return "";
        }
        StringBuffer result = new StringBuffer();
        int n = 0;
        char temp;
        boolean isCode = false; // 是不是HTML代码
        boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
        for (int i = 0; i < param.length(); i++) {
            temp = param.charAt(i);
            if (temp == '<') {
                isCode = true;
            } else if (temp == '&') {
                isHTML = true;
            } else if (temp == '>' && isCode) {
                n = n - 1;
                isCode = false;
            } else if (temp == ';' && isHTML) {
                isHTML = false;
            }
            try {
                if (!isCode && !isHTML) {
                    n += String.valueOf(temp).getBytes("GBK").length;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            if (n <= length - 3) {
                result.append(temp);
            } else {
                result.append("...");
                break;
            }
        }
        // 取出截取字符串中的HTML标记
        String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");
        // 去掉不需要结素标记的HTML标记
        temp_result = temp_result.replaceAll(
                "</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
                "");
        // 去掉成对的HTML标记
        temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
        // 用正则表达式取出标记
        Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
        Matcher m = p.matcher(temp_result);
        List<String> endHTML = new ArrayList();
        while (m.find()) {
            endHTML.add(m.group(1));
        }
        // 补全不成对的HTML标记
        for (int i = endHTML.size() - 1; i >= 0; i--) {
            result.append("</");
            result.append(endHTML.get(i));
            result.append(">");
        }
        return result.toString();
    }


    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }


    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }


    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }


    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }


    /**
     * 获得i18n字符串
     */
//    public static String getMessage(String code, Object[] args) {
//        LocaleResolver localLocaleResolver = (LocaleResolver) SpringContextHolder.getBean(LocaleResolver.class);
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                .getRequest();
//        Locale localLocale = localLocaleResolver.resolveLocale(request);
//        return SpringContextHolder.getApplicationContext().getMessage(code, args, localLocale);
//    }


    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }


    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
     *         toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }


        s = s.toLowerCase();


        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);


            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }


        return sb.toString();
    }


    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
     *         toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }


    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase("hello_world") == "helloWorld"
     *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
     *         toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }


        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);


            boolean nextUpperCase = true;


            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }


            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }


            sb.append(Character.toLowerCase(c));
        }


        return sb.toString();
    }


    /**
     * 如果不为空，则设置值
     *
     * @param target
     * @param source
     */
    public static void setValueIfNotBlank(String target, String source) {
        if (isNotBlank(source)) {
            target = source;
        }
    }


    /**
     * 转换为JS获取对象值，生成三目运算返回结果
     *
     * @param objectString
     *            对象串 例如：row.user.id
     *            返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public static String jsGetVal(String objectString) {
        StringBuilder result = new StringBuilder();
        StringBuilder val = new StringBuilder();
        String[] vals = split(objectString, ".");
        for (int i = 0; i < vals.length; i++) {
            val.append("." + vals[i]);
            result.append("!" + (val.substring(1)) + "?'':");
        }
        result.append(val.substring(1));
        return result.toString();
    }


    /**
     * 判断是否为手机号 格式1开头，11位数就满足了
     *
     * @param mobile
     * @return
     */
    public static boolean isMobileNO(String mobile) {
        if (!StringUtils.isBlank(mobile)) {
            Pattern p = Pattern.compile("^1\\d{10}$");
            Matcher m = p.matcher(mobile);
            return m.matches();
        } else {
            return false;
        }
    }


    /**
     * 根据特定字符生成size位的随机数
     *
     * @param size
     * @param sources
     *            为空时默认值为0123456789ABCDEFGHJKLMNOPQRSTUVWXYZ
     * @return
     */
    public static String getRandomString(int size, String sources) {
        if (StringUtils.isEmpty(sources)) {
            sources = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZ";
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

    /**
     * 生成业务流水号
     * 系统标识（sysFlg.length位）+时间（14位，年月日时分秒）+系统流水（randomCount位）
     *
     * @param sysFlg        系统标识
     * @param randomCount   随机数位数
     * @return
     */
    public static synchronized String createSerial(String sysFlg, int randomCount){
        safeSleep(1);
        SimpleDateFormat sdft = new SimpleDateFormat("yyyyMMddHHmmss");
        Date nowdate = new Date();
        String str = sdft.format(nowdate);
        return sysFlg + str + RandomStringUtils.randomNumeric(randomCount);
    }

    /**
     * @Title：createStr
     * @Description：生成字符串，格式为:"sz,2017-03-20 13:00:00,2017-03-20 14:00:00,2017-03-20 13"用于在触发器中获取这些参数
     * @param flg 城市代号
     * @return String 返回类型
     */
    public static synchronized String createStr(String flg){
        safeSleep(1);
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH");
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHH");
        Date nowdate = new Date();
        String beginDate = sdft.format(nowdate)+":00:00";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)+1);
        String endDate = sdft.format(cal.getTime())+":00:00";
        String dateStr = sd.format(nowdate);
        return flg +","+ beginDate +","+endDate+","+dateStr;
    }

    public static void safeSleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否含有emoj表情
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        boolean isEmoji = false;
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i + 1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                // non surrogate
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d
                        || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c
                        || hs == 0x2b1b || hs == 0x2b50 || hs == 0x231a) {
                    return true;
                }
                if (!isEmoji && source.length() > 1 && i < source.length() - 1) {
                    char ls = source.charAt(i + 1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        return isEmoji;
    }


    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }


    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }


    /**
     * @Title：getDateLength
     * @Description：计算年龄
     * @param from
     * @param to
     * @return String 返回类型
     */
    public static String getDateLength(Date from,Date to){
        if(from == null || to == null){
            return 0+"";
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(from);
        c2.setTime(to);
        int from_year = c1.get(Calendar.YEAR);
        int from_month = c1.get(Calendar.MONTH)+1;
        int from_day = c1.get(Calendar.DAY_OF_MONTH);
        int to_year = c2.get(Calendar.YEAR);
        int to_month = c2.get(Calendar.MONTH)+1;
        int to_day = c2.get(Calendar.DAY_OF_MONTH);
        int age = 0;
        if(to_year - from_year>0){
            age = to_year - from_year;
            if(to_month - from_month<0){
                age = age - 1;
            }else if(to_month - from_month==0){
                if(to_day - from_day < 0){
                    age = age - 1;
                }
            }
        }
        return age+"";
    }

    /**
     * @Title：countAge
     * @Description：计算年龄
     * @param from  生日
     * @return String 返回类型
     */
    public static String countAge(Date from){
        String age = "";
        int year = 0;
        int month = 0;
        int day = 0;
        if(from == null){
            age = age + 0;
        } else {
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(from);
            c2.setTime(new Date());
            if (c1.after(c2)) {
                throw new IllegalArgumentException("生日不能超过当前日期");
            }
            int from_year = c1.get(Calendar.YEAR);
            int from_month = c1.get(Calendar.MONTH)+1;
            int from_day = c1.get(Calendar.DAY_OF_MONTH);
            System.out.println("以前："+from_year + "-" + from_month + "-" + from_day);
            int MaxDayOfMonth = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
            //System.out.println(MaxDayOfMonth);
            int to_year = c2.get(Calendar.YEAR);
            int to_month = c2.get(Calendar.MONTH)+1;
            int to_day = c2.get(Calendar.DAY_OF_MONTH);
            System.out.println("现在："+to_year+"-"+to_month+"-"+to_day);

            year = to_year - from_year;
            if(c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR) < 0){
                year = year -1;
            }
            if(year < 1){// 小于一岁要精确到月份和天数
                System.out.println("--------");
                if(to_month - from_month>0){
                    month = to_month -from_month;
                    if(to_day - from_day < 0){
                        month = month - 1;
                        day = to_day -from_day + MaxDayOfMonth;
                    } else {
                        day= to_day -from_day;
                    }
                }else if(to_month - from_month==0){
                    if(to_day - from_day > 0){
                        day = to_day -from_day;
                    }
                }
            }
            if(year > 1){
                age = age + year + "岁";
            }else if(month > 0) {
                age = age + month + "个月" + day + "天";
            } else {
                age=age + day + "天";
            }
        }
        //System.out.println(year + "--" + month + "--" + day);
        return age;
    }

    /**
     * @Description 去除字符串的两边指定的字符
     * @author liangchao
     * @date 2018/6/27 19:21
     * @param args	目标字符串
     * @param @param beTrim	要去除的字符
     * @return String    返回类型
     */
    public static String trimConfirmedStr(String args,char beTrim) {
        int st = 0;
        int len = args.length();

        char[] val = args.toCharArray();
        char sbeTrim = beTrim;

        while ((st < len) && (val[st] <= sbeTrim)) {
            st++;
        }

        while ((st < len) && (val[len - 1] <= sbeTrim)) {
            len--;
        }
        return ((st > 0) || (len < args.length())) ? args.substring(st, len) : args;
    }

   /**
    * @Description 清除字符串中的空格
    * @author liangchao
    * @date 2018/6/27 19:25
    * @param str
    * @return
    */
    public static String removeStrSpace(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        Pattern SPACE_PATTERN = Pattern.compile("\\s|　|\t|\r|\n");
        Matcher matcher = SPACE_PATTERN.matcher(str);
        return matcher.replaceAll("");
    }

    /**
     * @Description 获取随机字符串
     * @author liangchao
     * @date 2018/6/27 19:27
     * @param length 字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String concat(Object... values) {
        return concatReplaceNulls("", values);
    }

    public static String concatReplaceNulls(String nullValue, Object... values) {
        if (values == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            Object[] var3 = values;
            int var4 = values.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Object value = var3[var5];
                if (value == null) {
                    sb.append(nullValue);
                } else {
                    sb.append(value.toString());
                }
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {
/*String string = "This is a smiley \uD83C\uDFA6 face\uD860\uDD5D \uD860\uDE07 \uD860\uDEE2 \uD863\uDCCA \uD863\uDCCD \uD863\uDCD2 \uD867\uDD98 ";
   System.out.println(containsEmoji(string));
   System.out.println(filterEmoji(string));*/
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date from = sdf.parse(null);
        System.out.println(countAge(from));
    }


}

package core.web.json;

import com.integrated.utils.common.DateUtils;
import com.integrated.utils.common.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: JsonResponse
 * Description: 定义输出类型
 * Author: liangchao
 * Date: 2018/5/9 12:58
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class JsonResponse <T> implements Serializable {

    private String retCode;
    private String retDesc;
    private String errorMeg;
    private String timestamp;
    private T rspBody;

    public JsonResponse() {
        this(null);
    }

    public JsonResponse(T rspBody) {
        this(BasicResponseCode.SUCCESS.statusKey, BasicResponseCode.SUCCESS.statusVal,
                rspBody);
    }

    public JsonResponse(String retCode ,String retDesc) {
        this(retCode, retDesc, null);
    }

    public JsonResponse(String retCode ,String retDesc ,T rspBody) {
        this(retCode, retDesc, DateUtils.getNowTime(), rspBody);
    }

    public JsonResponse(String retCode ,String retDesc ,String timestamp ,T rspBody) {
        this.retCode = retCode;
        this.retDesc = retDesc;
        this.timestamp = timestamp;
        this.rspBody = rspBody;
    }

    public JsonResponse(String retCode, String retDesc, String timestamp, String errorMeg, T rspBody) {
        this.retCode = retCode;
        this.retDesc = retDesc;
        this.errorMeg = errorMeg;
        this.timestamp = timestamp;
        this.rspBody = rspBody;
    }

    public static JsonResponse failRespose() {
        return failRespose(new Exception(StringUtils.EMPTY));
    }

    public static JsonResponse failRespose(Exception e) {
        return new JsonResponse(BasicResponseCode.FAIL.statusKey
                , BasicResponseCode.FAIL.statusVal
                , DateUtils.getNowTime()
                , e.getMessage(), null);
    }

    public static JsonResponse exceptionRespose() {
        return exceptionRespose(new Exception(StringUtils.EMPTY));
    }

    public static JsonResponse exceptionRespose(Exception e) {
        return new JsonResponse(BasicResponseCode.EXCEPTION.statusKey
                , BasicResponseCode.EXCEPTION.statusVal
                , DateUtils.getNowTime()
                , e.getMessage(), null);
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetDesc() {
        return retDesc;
    }

    public void setRetDesc(String retDesc) {
        this.retDesc = retDesc;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorMeg() {
        return errorMeg;
    }

    public void setErrorMeg(String errorMeg) {
        this.errorMeg = errorMeg;
    }

    public T getRspBody() {
        return rspBody;
    }

    public void setRspBody(T rspBody) {
        this.rspBody = rspBody;
    }

    public enum BasicResponseCode{
        //通用响应信息
        SUCCESS("0000000","处理成功！"), //处理成功
        FAIL("0100001","处理失败！"), //处理失败（自定义抛出异常）
        EXCEPTION("0100002","服务器异常！");//服务器异常

        private String statusKey;
        private String statusVal;

        BasicResponseCode(String statusKey, String statusVal) {
            this.statusKey = statusKey;
            this.statusVal = statusVal;
        }

        public static String getValByKey(String statusKey) {
            for (BasicResponseCode e: BasicResponseCode.values()) {
                if(e.getStatusKey().equals(statusKey)) {
                    return e.getStatusVal();
                }
            }
            return "";
        }

        public static Map<String, String> getEnumMap() {
            Map<String, String> enumMap = new HashMap<>();
            for (BasicResponseCode e: BasicResponseCode.values()) {
                enumMap.put(e.getStatusKey(), e.getStatusVal());
            }
            return enumMap;
        }

        public static String getValue(String statusKey) {
            Map<String, String> enumMap = BasicResponseCode.getEnumMap();

            return null == enumMap || null == enumMap.get(statusKey) ? "" : enumMap.get(statusKey);
        }

        public static List<Map<String, String>> convertToList() {
            List<Map<String,String>> result = new ArrayList<>();
            BasicResponseCode[] statusArr = BasicResponseCode.values();
            for (BasicResponseCode status : statusArr) {
                Map<String, String>  resultMap = new HashMap<>();
                resultMap.put("statusKey", status.statusKey);
                resultMap.put("statusVal",status.statusVal);
                result.add(resultMap);
            }
            return result;
        }

        public String getStatusKey() {
            return statusKey;
        }

        public Integer getKeyIntVal() {
            return Integer.parseInt(statusKey);
        }

        public void setStatusKey(String statusKey) {
            this.statusKey = statusKey;
        }

        public String getStatusVal() {
            return statusVal;
        }

        public void setStatusVal(String statusVal) {
            this.statusVal = statusVal;
        }
        }
}

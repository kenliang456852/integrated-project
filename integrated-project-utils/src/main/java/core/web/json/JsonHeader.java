package core.web.json;

/**
 * ClassName: JsonHeader
 * Description:
 * Author: liangchao
 * Date: 2018/7/22 19:59
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class JsonHeader {

    public JsonHeader() {
    }

    public JsonHeader(String appId, String chanalId, String ip, String sid) {
        this.appId = appId;
        this.chanalId = chanalId;
        this.ip = ip;
        this.sid = sid;
    }

    private String appId;

    private String chanalId;

    private String ip;

    private String sid;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getChanalId() {
        return chanalId;
    }

    public void setChanalId(String chanalId) {
        this.chanalId = chanalId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "JsonHeader{" + "appId='" + appId + '\'' + ", chanalId='" + chanalId + '\'' + ", ip='" + ip + '\''
                + ", sid='" + sid + '\'' + '}';
    }
}

package com.integrated.utils.common;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: IpUtils
 * Description:
 * Author: liangchao
 * Date: 2018/5/12 17:47
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class IpUtils {


    public static String INTRANET_IP = getIntranetIp(); // 内网IP
    public static String INTERNET_IP = getV4IP(); // 本机外网IP
    private static String GETWAYADDRESSPREX;

    private IpUtils(){}

    @Value("${http.sm.url}")
    public void setGETWAYADDRESSPREX(String GETWAYADDRESSPREX) {
        IpUtils.GETWAYADDRESSPREX = GETWAYADDRESSPREX;
    }

    /**
     * 获得本机内网IP
     * @return 内网IP
     */
    public static String getIntranetIp(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得本机外网IP
     * @return 外网IP
     */
    public static String getV4IP(){
        String ip = "";
        String chinaz = "http://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            while((read=in.readLine())!=null){
                inputLine.append(read+"\r\n");
            }
            //System.out.println(inputLine.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if(m.find()){
            String ipstr = m.group(1);
            ip = ipstr;
            //System.out.println(ipstr);
        }
        return ip;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                    return INTERNET_IP;
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }

//    public static DictTreeDTO getProvince() throws Exception {
//        JSONObject json = readJsonFromUrl(
//                "http://api.map.baidu.com/location/ip?ak=Ne2bfTk3whTNHWVBz4d4ZI3ptB81x26K&ip=" + INTERNET_IP);
//        DictTreeDTO dictTreeDTO = null;
//        if (json.get("status").toString().equals("0")) {
//            if (json.get("content") != null) {
//                Map contentMap = (Map) json.get("content");
//                if (contentMap.get("address_detail") != null) {
//                    Map addressDetailMap = (Map) contentMap.get("address_detail");
//                    if (addressDetailMap.get("province").toString() != null) {
//                        // 根据名称查询城市code
//                        JsonRequest<Map> mapJsonRequest = new JsonRequest<>();
//                        HashMap<String, Object> hashMap = new HashMap<>();
//                        hashMap.put("areaName", addressDetailMap.get("province").toString());
//                        hashMap.put("areaDeep", AreaDeepEnum.AREA_DEEP_PROVINCE.getCode());
//                        mapJsonRequest.setReqBody(hashMap);
//                        dictTreeDTO = getDictTreeDTO(mapJsonRequest);
//                    }
//                }
//            }
//        }
//        return dictTreeDTO;
//    }
//
//    private static DictTreeDTO getDictTreeDTO(JsonRequest<Map> mapJsonRequest) throws Exception {
//        DictTreeDTO dictTreeDTO = new DictTreeDTO();
//        String dictAreaInfoResult = ServiceGatewayUtils.invokePost(GETWAYADDRESSPREX+
//                        "/dictAreaInfo",
//                JSON.toJSONString(mapJsonRequest));
//        JsonResponse dictAreaInfoResultJson = JsonUtil.parseObject(dictAreaInfoResult,
//                JsonResponse.class);
//        HashMap<String, Object> rspBody = (HashMap<String, Object>) dictAreaInfoResultJson.getRspBody();
//        Integer areaId = (Integer) rspBody.get("areaId");
//        String areaName = (String) rspBody.get("areaName");
//        Integer parentAreaId = (Integer) rspBody.get("parentAreaId");
//        if (areaId != null) {
//            // 组装返回对象
//            dictTreeDTO = new DictTreeDTO();
//            dictTreeDTO.setParentCode(parentAreaId.toString());
//            dictTreeDTO.setDictTreeCode(areaId.toString());
//            dictTreeDTO.setDictTreeName(areaName);
//        }
//        return dictTreeDTO;
//    }
//
//    public static DictTreeDTO getCity() throws Exception {
//        JSONObject json = readJsonFromUrl(
//                "http://api.map.baidu.com/location/ip?ak=G86SpLQVWp1YSgZS3KLCKfdT48lbgRGN&ip=" + INTERNET_IP);
//        DictTreeDTO dictTreeDTO = null;
//        if (json.get("status").toString().equals("0")) {
//            if (json.get("content") != null) {
//                Map contentMap = (Map) json.get("content");
//                if (contentMap.get("address_detail") != null) {
//                    Map addressDetailMap = (Map) contentMap.get("address_detail");
//                    if (addressDetailMap.get("city").toString() != null) {
//                        // 根据名称查询城市code
//                        JsonRequest<Map> mapJsonRequest = new JsonRequest<>();
//                        HashMap<String, Object> hashMap = new HashMap<>();
//                        hashMap.put("areaName", addressDetailMap.get("city").toString());
//                        hashMap.put("areaDeep", AreaDeepEnum.AREA_DEEP_CITY.getCode());
//                        mapJsonRequest.setReqBody(hashMap);
//                        dictTreeDTO = getDictTreeDTO(mapJsonRequest);
//                    }
//                }
//            }
//        }
//        return dictTreeDTO;
//    }
//
//    private static String readAll(Reader rd) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        int cp;
//        while ((cp = rd.read()) != -1) {
//            sb.append((char) cp);
//        }
//        return sb.toString();
//    }
//
//    private static JSONObject readJsonFromUrl(String url) throws Exception {
//        InputStream is = new URL(url).openStream();
//        try {
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//            String jsonText = readAll(rd);
//            JSONObject json = JSONObject.parseObject(jsonText);
//            return json;
//        } finally {
//            is.close();
//        }
//    }
}

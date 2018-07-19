package com.integrated.utils.common;

import org.apache.commons.collections.MapUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: EnumUtils
 * Description:
 * Author: liangchao
 * Date: 2018/5/23 11:30
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class EnumUtils {

    /**
     * 枚举转List
     * @param enumT
     * @return enum mapcolloction
     */
    public static <E extends Enum<E>> List<Map<Object, String>> enum2List(final Class<E> enumT,
            String... methodNames) {
        ArrayList<Map<Object, String>> list = new ArrayList<>();
        Map<Object, String> enum2Map = enum2Map(enumT, methodNames);
        if(MapUtils.isNotEmpty(enum2Map)){
            for (Map.Entry<Object, String> entry : enum2Map.entrySet()) {
                HashMap<Object, String> map = new HashMap<>();
                map.put("key",entry.getKey().toString());
                map.put("value",entry.getValue().toString());
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 枚举转map结合value作为map的key,description作为map的value
     * @param enumT
     * @return enum mapcolloction
     */
    public static <E extends Enum<E>> Map<Object, String> enum2Map(final Class<E> enumT,
            String... methodNames) {
        Map<Object, String> enummap = new HashMap<Object, String>();
        if (!enumT.isEnum()) {
            return enummap;
        }
        E[] enums = enumT.getEnumConstants();
        if (enums == null || enums.length <= 0) {
            return enummap;
        }
        int count = methodNames.length;
        String valueMathod = "getCode"; //默认接口value方法
        String desMathod = "getDesc";//默认接口description方法
        if (count >= 1 && !"".equals(methodNames[0])) { //扩展方法
            valueMathod = methodNames[0];
        }
        if (count == 2 && !"".equals(methodNames[1])) {
            desMathod = methodNames[1];
        }
        for (int i = 0, len = enums.length; i < len; i++) {
            E tobj = enums[i];
            try {
                Object resultValue = getMethodValue(valueMathod, tobj); //获取value值
                if ("".equals(resultValue)) {
                    continue;
                }
                Object resultDes = getMethodValue(desMathod, tobj); //获取description描述值
                if ("".equals(resultDes)) { //如果描述不存在获取属性值
                    resultDes = tobj;
                }
                enummap.put(resultValue, resultDes + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return enummap;
    }


    /**
     * 根据反射，通过方法名称获取方法值，忽略大小写的
     * @param methodName
     * @param obj
     * @param args
     * @return return value
     */
    private static <T> Object getMethodValue(String methodName, T obj,
            Object... args) {
        Object resut = "";
        // boolean isHas = false;
        try {
            /********************************* start *****************************************/
            Method[] methods = obj.getClass().getMethods(); //获取方法数组，这里只要共有的方法
            if (methods.length <= 0) {
                return resut;
            }
            Method method = null;
            for (int i = 0, len = methods.length; i < len; i++) {
                if (methods[i].getName().equalsIgnoreCase(methodName)) { //忽略大小写取方法
                    method = methods[i];
                    break;
                }
            }

            /*************************** end ***********************************************/
            if (method == null) {
                return resut;
            }
            resut = method.invoke(obj, args); //方法执行
            if (resut == null) {
                resut = "";
            }
            return resut; //返回结果
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resut;
    }
    /**
     * 通过value值获取对应的描述信息
     * @param value
     * @param enumT
     * @return enum description
     */
    public static <E extends Enum<E>> Object getEnumDescriotionByValue(Object value,
            final Class<E> enumT, String... methodNames) {

        E[] enums = enumT.getEnumConstants();
        if (enums == null || enums.length <= 0) {
            return "";
        }
        int count = methodNames.length;
        String valueMathod = "getValue";    //默认获取枚举value方法，与接口方法一致
        String desMathod = "getDescription"; //默认获取枚举description方法
        if (count >= 1 && !"".equals(methodNames[0])) {
            valueMathod = methodNames[0];
        }
        if (count == 2 && !"".equals(methodNames[1])) {
            desMathod = methodNames[1];
        }
        for (int i = 0, len = enums.length; i < len; i++) {
            E e = enums[i];
            try {
                Object resultValue = getMethodValue(valueMathod, e);//获取枚举对象value
                if (resultValue.toString().equals(value + "")) {
                    Object resultDes = getMethodValue(desMathod, e); //存在则返回对应描述
                    return resultDes;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }

}

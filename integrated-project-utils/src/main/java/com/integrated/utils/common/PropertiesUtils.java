package com.integrated.utils.common;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * ClassName: PropertiesUtils
 * Description:
 * Author: liangchao
 * Date: 2018/4/25 19:03
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class PropertiesUtils {

    public static Properties getPropertyFile(String propertyName) {
        Properties props=new Properties();
        try {
            props = PropertiesLoaderUtils.loadAllProperties(propertyName);
        } catch (IOException e) {
            throw new RuntimeException("配置文件加载失败");
        }
        return  props;
    }

    public static String getPropertyValue(String propertyName, String key) {
        Properties properties = getPropertyFile(propertyName);
        return properties.getProperty(key);
    }

}

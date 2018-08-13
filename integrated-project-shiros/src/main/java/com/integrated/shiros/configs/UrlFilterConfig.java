package com.integrated.shiros.configs;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ClassName: UrlFilterConfig
 * Description:
 * Author: liangchao
 * Date: 2018/8/8 10:54
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
@Configuration
@ConfigurationProperties(prefix="urlfilter")
public class UrlFilterConfig {
    private LinkedHashMap<String, String> startwith;
    private LinkedHashMap<String, String> endwith;
    private LinkedHashMap<String, String> completely;

    public LinkedHashMap<String, String> getStartwith() {
        return startwith;
    }

    public void setStartwith(LinkedHashMap<String, String> startwith) {
        this.startwith = startwith;
    }

    public LinkedHashMap<String, String> getEndwith() {
        return endwith;
    }

    public void setEndwith(LinkedHashMap<String, String> endwith) {
        this.endwith = endwith;
    }

    public LinkedHashMap<String, String> getCompletely() {
        return completely;
    }

    public void setCompletely(LinkedHashMap<String, String> completely) {
        this.completely = completely;
    }

    public boolean check(String value) {
        for(Map.Entry<String, String> entry : completely.entrySet()) {
            if(StringUtils.equalsIgnoreCase(value, entry.getValue())) {
                return true;
            }
        }
        for(Map.Entry<String, String> entry : startwith.entrySet()) {
            if(StringUtils.startsWithIgnoreCase(value, entry.getValue())) {
                return true;
            }
        }
        for(Map.Entry<String, String> entry : endwith.entrySet()) {
            if(StringUtils.endsWithIgnoreCase(value, entry.getValue())) {
                return true;
            }
        }

        return false;
    }
}

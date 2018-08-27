package com.integrated.shiros.controller;

import com.integrated.utils.common.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: ToPageController
 * Description:
 * Author: liangchao
 * Date: 2018/7/22 18:56
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
@Controller
public class RestfulController {
    private static final String SPLIT_FOLDER_PATH = "#";

    @GetMapping(value = {"/{pageName}.html","/{folderName}/{pageName}.html"})
    public String toPage(@PathVariable(required = false) String folderName, @PathVariable String pageName) {
        if(StringUtils.isNotBlank(folderName)) {
            String folderPath = StringUtils.EMPTY;
            for(String str : folderName.split(SPLIT_FOLDER_PATH)) {
                if(StringUtils.isNotBlank(str)) {
                    folderPath += str;
                    folderPath += StringUtils.FOLDER_DELIMITER;
                }
            }
            return StringUtils.concat(folderPath, pageName);
        }
        return pageName;
    }

}

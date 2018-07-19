package com.integrated.utils.common;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ListUtils
 * Description:
 * Author: liangchao
 * Date: 2018/5/11 15:14
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class ListUtils {

    /**
     * @Description 如果是一个null list  返回一个空的ArrayList
     * @author liangchao
     * @date 2018/5/11 15:15
     * @param list
     * @return
     */
    public List ifNullReturnEmpty(List list) {
        return CollectionUtils.isEmpty(list) ? new ArrayList() : list;
    }
}

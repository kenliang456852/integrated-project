package com.integrated.classloader;
import org.apache.xmlbeans.impl.tool.BaseSchemaResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName: MyManager
 * Description: BaseManager的子类，此类需要实现java类的热加载功能
 * Author: liangchao
 * Date: 2018/8/2 20:20
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class MyManager implements BaseManager {
    private Logger logger = LoggerFactory.getLogger(MyManager.class);
    @Override
    public void logic() {
        System.out.println("学习类加载模式");
    }
}

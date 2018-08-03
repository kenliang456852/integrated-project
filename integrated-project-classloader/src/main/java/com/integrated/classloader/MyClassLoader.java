package com.integrated.classloader;

import com.integrated.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * ClassName: MyClassLoader
 * Description:
 * Author: liangchao
 * Date: 2018/8/2 17:14
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class MyClassLoader extends ClassLoader {
    private Logger logger = LoggerFactory.getLogger(MyClassLoader.class);

//    要加载的java类的classpath路径
    private String classpath;

    public MyClassLoader(String classpath) {
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] dateA = this.loadClassDate(name);
        return super.findClass(name);
    }

    private byte[] loadClassDate(String name) {
        name.replace(".", "//");
        FileInputStream is = null;
        try {
             is = new FileInputStream(
                    new File(StringUtils.concat(
                            this.classpath, name, ".class")));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int b = 0;
            while ((b = is.read()) != -1) {
                byteArrayOutputStream.write(b);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
            try {
                if(null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

}

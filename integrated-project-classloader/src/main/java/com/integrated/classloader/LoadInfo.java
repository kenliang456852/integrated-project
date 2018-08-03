package com.integrated.classloader;
/**
 * ClassName: LoadInfo
 * Description:
 * Author: liangchao
 * Date: 2018/8/2 20:39
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class LoadInfo {
    /**
     * @Description 自定义的类加载
     * @author liangchao
     * @date 2018/8/2 20:43
     */
    private MyClassLoader myLoader;

    /**
     * @Description 记录加载类的时间戳
     * @author liangchao
     * @date 2018/8/2 20:43
     */
    private Long loadTime;

    /**
     * @Description BaseManager
     * @author liangchao
     * @date 2018/8/2 20:43
     */
    private BaseManager manager;

    public LoadInfo() {
    }

    public LoadInfo(MyClassLoader myLoader, Long loadTime) {
        this.myLoader = myLoader;
        this.loadTime = loadTime;
    }

    public LoadInfo(MyClassLoader myLoader, Long loadTime, BaseManager manager) {
        this.myLoader = myLoader;
        this.loadTime = loadTime;
        this.manager = manager;
    }

    public MyClassLoader getMyLoader() {
        return myLoader;
    }

    public void setMyLoader(MyClassLoader myLoader) {
        this.myLoader = myLoader;
    }

    public Long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Long loadTime) {
        this.loadTime = loadTime;
    }

    public BaseManager getManager() {
        return manager;
    }

    public void setManager(BaseManager manager) {
        this.manager = manager;
    }
}

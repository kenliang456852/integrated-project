package com.integrated.shiros.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ClassName: ShiroConfiguration
 * Description:
 * Author: liangchao
 * Date: 2018/7/22 0:18
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
//@Configuration
public class ShiroConfiguration {
//    @SuppressWarnings("unused") private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
//
//    public EhCacheManager getEhCacheManager() {
//        EhCacheManager em = new EhCacheManager();
//        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
//        return em;
//    }
//
//    /**
//     * 注册DelegatingFilterProxy（Shiro）
//     *
//     * @return
//     */
//    @Bean public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//        // 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
//        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
//        filterRegistration.setEnabled(true);
//        filterRegistration.addUrlPatterns("/*");
//        return filterRegistration;
//    }
//
//    @Bean(name = "lifecycleBeanPostProcessor") public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
//        daap.setProxyTargetClass(true);
//        return daap;
//    }
//
//    @Bean(name = "securityManager")
//    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroRealm myShiroRealm) {
//        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
//        dwsm.setRealm(myShiroRealm);
//        // <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
//        dwsm.setCacheManager(getEhCacheManager());
//        return dwsm;
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(MyShiroRealm myShiroRealm) {
//        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
//        aasa.setSecurityManager(getDefaultWebSecurityManager(myShiroRealm));
//        return new AuthorizationAttributeSourceAdvisor();
//    }
//
//    /**
//     * 加载shiroFilter权限控制规则（从数据库读取然后配置)
//     *
//     * @param shiroFilterFactoryBean
//     * @param stuService
//     * @param scoreDao
//     */
//    @Bean(name = "shiroFilter")
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean(MyShiroRealm myShiroRealm) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager(myShiroRealm));
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        shiroFilterFactoryBean.setSuccessUrl("/login_enter");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        filterChainDefinitionMap.put("/test", "anon");
//        filterChainDefinitionMap.put("/bbbb", "authc,perms[emp]");
//		/*filterChainDefinitionMap.put("/testtt", "authc");
//		filterChainDefinitionMap.put("/**", "user");*/
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }

}

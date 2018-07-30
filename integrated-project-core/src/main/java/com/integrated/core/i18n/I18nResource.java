package com.integrated.core.i18n;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.MissingResourceException;

/**
 * ClassName: I18nResource
 * Description:
 * Author: liangchao
 * Date: 2018/7/30 16:33
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class I18nResource {
    private static ReloadableResourceBundleMessageSource i18nMessage = new ReloadableResourceBundleMessageSource();
    private static ReloadableResourceBundleMessageSource i18nException;

    public I18nResource() {
    }

    public static String getMessage(String messageKey, Object[] messageArgs) {
        String message = null;

        try {
            message = i18nMessage.getMessage(messageKey, messageArgs, LocaleContextHolder.getLocale());
        } catch (MissingResourceException var4) {
            message = messageKey; //"i18nMessage is: " + messageKey + ", can't get the I18n properties";
        } catch (Throwable var5) {
            message = messageKey; //"i18nMessage is: " + messageKey + ", can't get the I18n properties";
        }

        return message;
    }

    public static String getException(String code, Object[] messageArgs) {
        String message = null;

        try {
            message = i18nException.getMessage(code, messageArgs, LocaleContextHolder.getLocale());
        } catch (MissingResourceException var4) {
            message = code; //"i18nException is: " + code + ", can't get the I18n properties";
        } catch (Throwable var5) {
            message = code; //"i18nException is: " + code + ", can't get the I18n properties";
        }

        return message;
    }

    public static String getMessage(String messageKey) {
        return getMessage(messageKey, (Object[])null);
    }

    static {
        i18nMessage.setBasenames(new String[]{"config/message", "classpath:config/message"});
        i18nException = new ReloadableResourceBundleMessageSource();
        i18nException.setBasenames(new String[]{"config/exception", "classpath:config/exception"});
    }
}

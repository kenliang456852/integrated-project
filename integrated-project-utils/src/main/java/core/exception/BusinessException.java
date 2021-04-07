package core.exception;

import com.integrated.core.i18n.I18nResource;

/**
 * ClassName: BusinessException
 * Description:
 * Author: liangchao
 * Date: 2018/7/30 16:31
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class BusinessException extends RuntimeException {

    private String errorCode;
    protected Object[] errorArgs = null;

    public BusinessException(String exceptionCode) {
        this.errorCode = exceptionCode;
    }

    public BusinessException(String exceptionCode, Object[] errorArgs) {
        this.errorCode = exceptionCode;
        this.errorArgs = errorArgs;
    }

    public BusinessException(String exceptionCode, Object[] errorArgs, Throwable cause) {
        super(cause);
        this.errorCode = exceptionCode;
        this.errorArgs = errorArgs;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public Object[] getErrorArgs() {
        return this.errorArgs;
    }

    public String getMessage() {
        return I18nResource.getException(this.errorCode, this.errorArgs);
    }
}

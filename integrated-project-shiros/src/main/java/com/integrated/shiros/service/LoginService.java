package com.integrated.shiros.service;

import com.integrated.shiros.model.BusiAcctInfo;
import com.integrated.shiros.model.PermissionInfo;
import com.integrated.shiros.model.RoleInfo;

import java.util.List;

/**
 * ClassName: LoginService
 * Description:
 * Author: liangchao
 * Date: 2018/7/20 23:17
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public interface LoginService {
    BusiAcctInfo getBusiAcctInfoByUserName(String userName);

    List<RoleInfo> getRoleListByAcctId(String acctId);

    List<RoleInfo> getRoleListByAcctName(String acctName);

    List<PermissionInfo> getPermissionByAcctName(String acctName);
}

package com.integrated.shiros.service.impl;

import com.integrated.shiros.dao.BusiAcctInfoDao;
import com.integrated.shiros.dao.PermissionInfoDao;
import com.integrated.shiros.dao.RoleInfoDao;
import com.integrated.shiros.model.BusiAcctInfo;
import com.integrated.shiros.model.PermissionInfo;
import com.integrated.shiros.model.RoleInfo;
import com.integrated.shiros.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: LoginServiceImpl
 * Description:
 * Author: liangchao
 * Date: 2018/7/20 23:18
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private BusiAcctInfoDao busiAcctInfoDao;
    @Autowired
    private RoleInfoDao roleInfoDao;
    @Autowired
    private PermissionInfoDao permissionInfoDao;

    @Override
    public BusiAcctInfo getBusiAcctInfoByUserName(String userName){
        return busiAcctInfoDao.getByUserName(userName);
    }

    @Override
    public List<RoleInfo> getRoleListByAcctId(String acctId) {
        List<RoleInfo> roleList = roleInfoDao.getListByAcctId(acctId);
        return roleList;
    }

    @Override
    public List<RoleInfo> getRoleListByAcctName(String acctName) {
        List<RoleInfo> roleList = roleInfoDao.getListByAcctName(acctName);
        return roleList;
    }

    @Override
    public List<PermissionInfo> getPermissionByAcctName(String acctName) {
        return permissionInfoDao.getListByAcctName(acctName);
    }
}

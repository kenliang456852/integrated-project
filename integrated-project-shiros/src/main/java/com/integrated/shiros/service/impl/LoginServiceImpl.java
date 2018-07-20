package com.integrated.shiros.service.impl;

import com.integrated.shiros.dao.BusiAcctInfoDao;
import com.integrated.shiros.model.BusiAcctInfo;
import com.integrated.shiros.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String getBusiAcctInfoByUserName(String userName){
        BusiAcctInfo busiAcctInfo = busiAcctInfoDao.getByUserName(userName);
        if(null != busiAcctInfo) {
            return busiAcctInfo.getPassword();
        }
        return null;
    }
}

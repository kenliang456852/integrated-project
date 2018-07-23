package com.integrated.shiros.service.impl;

import java.util.List;
import com.integrated.shiros.service.AcctRoleService;
import com.integrated.shiros.dao.AcctRoleDao;
import com.integrated.shiros.model.AcctRole;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * dal Interface:AcctRole
 * @author gencode
 * @date 2018-7-23
 */
@Service
public class AcctRoleServiceImpl implements AcctRoleService{

    @Autowired
    private AcctRoleDao acctRoleDao;

    public List<AcctRole> findAll(){
       return  acctRoleDao.findAll();
    }

    public List<AcctRole> findAcctRoles(AcctRole record){
       return  acctRoleDao.find(record);
    }

    public AcctRole getAcctRoleByPrimaryKey(String id){
        return acctRoleDao.getByPrimaryKey(id);
    }

    public Integer createAcctRole(AcctRole record){
        return acctRoleDao.insert(record);
    }

    public Integer removeAcctRole(AcctRole record){
        return acctRoleDao.delete(record);
    }
    
    public Integer removeByPrimaryKey(String id){
    	return acctRoleDao.deleteByPrimaryKey(id);
    }

    public Integer modifyAcctRoleByPrimaryKey(AcctRole record){
        return acctRoleDao.updateByPrimaryKey(record);
    }









}
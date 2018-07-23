package com.integrated.shiros.service.impl;

import java.util.List;
import com.integrated.shiros.service.RoleInfoService;
import com.integrated.shiros.dao.RoleInfoDao;
import com.integrated.shiros.model.RoleInfo;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * dal Interface:RoleInfo
 * @author gencode
 * @date 2018-7-23
 */
@Service
public class RoleInfoServiceImpl implements RoleInfoService{

    @Autowired
    private RoleInfoDao roleInfoDao;

    public List<RoleInfo> findAll(){
       return  roleInfoDao.findAll();
    }

    public List<RoleInfo> findRoleInfos(RoleInfo record){
       return  roleInfoDao.find(record);
    }

    public RoleInfo getRoleInfoByPrimaryKey(String id){
        return roleInfoDao.getByPrimaryKey(id);
    }

    public Integer createRoleInfo(RoleInfo record){
        return roleInfoDao.insert(record);
    }

    public Integer removeRoleInfo(RoleInfo record){
        return roleInfoDao.delete(record);
    }
    
    public Integer removeByPrimaryKey(String id){
    	return roleInfoDao.deleteByPrimaryKey(id);
    }

    public Integer modifyRoleInfoByPrimaryKey(RoleInfo record){
        return roleInfoDao.updateByPrimaryKey(record);
    }









}
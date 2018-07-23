package com.integrated.shiros.service.impl;

import java.util.List;
import com.integrated.shiros.service.PermissionInfoService;
import com.integrated.shiros.dao.PermissionInfoDao;
import com.integrated.shiros.model.PermissionInfo;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * dal Interface:PermissionInfo
 * @author gencode
 * @date 2018-7-23
 */
@Service
public class PermissionInfoServiceImpl implements PermissionInfoService{

    @Autowired
    private PermissionInfoDao permissionInfoDao;

    public List<PermissionInfo> findAll(){
       return  permissionInfoDao.findAll();
    }

    public List<PermissionInfo> findPermissionInfos(PermissionInfo record){
       return  permissionInfoDao.find(record);
    }

    public PermissionInfo getPermissionInfoByPrimaryKey(String id){
        return permissionInfoDao.getByPrimaryKey(id);
    }

    public Integer createPermissionInfo(PermissionInfo record){
        return permissionInfoDao.insert(record);
    }

    public Integer removePermissionInfo(PermissionInfo record){
        return permissionInfoDao.delete(record);
    }
    
    public Integer removeByPrimaryKey(String id){
    	return permissionInfoDao.deleteByPrimaryKey(id);
    }

    public Integer modifyPermissionInfoByPrimaryKey(PermissionInfo record){
        return permissionInfoDao.updateByPrimaryKey(record);
    }









}
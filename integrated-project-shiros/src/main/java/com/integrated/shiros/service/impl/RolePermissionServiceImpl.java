package com.integrated.shiros.service.impl;

import java.util.List;
import com.integrated.shiros.service.RolePermissionService;
import com.integrated.shiros.dao.RolePermissionDao;
import com.integrated.shiros.model.RolePermission;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * dal Interface:RolePermission
 * @author gencode
 * @date 2018-7-23
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService{

    @Autowired
    private RolePermissionDao rolePermissionDao;

    public List<RolePermission> findAll(){
       return  rolePermissionDao.findAll();
    }

    public List<RolePermission> findRolePermissions(RolePermission record){
       return  rolePermissionDao.find(record);
    }

    public RolePermission getRolePermissionByPrimaryKey(String id){
        return rolePermissionDao.getByPrimaryKey(id);
    }

    public Integer createRolePermission(RolePermission record){
        return rolePermissionDao.insert(record);
    }

    public Integer removeRolePermission(RolePermission record){
        return rolePermissionDao.delete(record);
    }
    
    public Integer removeByPrimaryKey(String id){
    	return rolePermissionDao.deleteByPrimaryKey(id);
    }

    public Integer modifyRolePermissionByPrimaryKey(RolePermission record){
        return rolePermissionDao.updateByPrimaryKey(record);
    }









}
package com.integrated.shiros.service;

import java.util.List;
import com.integrated.shiros.model.RolePermission;

/**
 * dal Interface:RolePermission
 * @author gencode
 * @date 2018-7-23
 */
public interface RolePermissionService {

    public List<RolePermission> findAll();

    public List<RolePermission> findRolePermissions(RolePermission record);

    public RolePermission getRolePermissionByPrimaryKey(String id);

    public Integer createRolePermission(RolePermission record);

    public Integer removeRolePermission(RolePermission record);

    public Integer removeByPrimaryKey(String id);

    public Integer modifyRolePermissionByPrimaryKey(RolePermission record);



}
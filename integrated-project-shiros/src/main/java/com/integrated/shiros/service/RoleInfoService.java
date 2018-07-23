package com.integrated.shiros.service;

import java.util.List;
import com.integrated.shiros.model.RoleInfo;

/**
 * dal Interface:RoleInfo
 * @author gencode
 * @date 2018-7-23
 */
public interface RoleInfoService {

    public List<RoleInfo> findAll();

    public List<RoleInfo> findRoleInfos(RoleInfo record);

    public RoleInfo getRoleInfoByPrimaryKey(String id);

    public Integer createRoleInfo(RoleInfo record);

    public Integer removeRoleInfo(RoleInfo record);

    public Integer removeByPrimaryKey(String id);

    public Integer modifyRoleInfoByPrimaryKey(RoleInfo record);



}
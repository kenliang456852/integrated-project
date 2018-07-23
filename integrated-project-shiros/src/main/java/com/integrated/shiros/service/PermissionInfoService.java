package com.integrated.shiros.service;

import java.util.List;
import com.integrated.shiros.model.PermissionInfo;

/**
 * dal Interface:PermissionInfo
 * @author gencode
 * @date 2018-7-23
 */
public interface PermissionInfoService {

    public List<PermissionInfo> findAll();

    public List<PermissionInfo> findPermissionInfos(PermissionInfo record);

    public PermissionInfo getPermissionInfoByPrimaryKey(String id);

    public Integer createPermissionInfo(PermissionInfo record);

    public Integer removePermissionInfo(PermissionInfo record);

    public Integer removeByPrimaryKey(String id);

    public Integer modifyPermissionInfoByPrimaryKey(PermissionInfo record);



}
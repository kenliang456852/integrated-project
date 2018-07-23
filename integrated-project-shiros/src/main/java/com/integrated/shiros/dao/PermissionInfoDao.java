package com.integrated.shiros.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.integrated.shiros.model.PermissionInfo;

/**
 * dal Interface:PermissionInfo
 * @author gencode
 */
public interface PermissionInfoDao {

	Integer insert(PermissionInfo record);

    Integer insertSelective(PermissionInfo record);
    
    Integer delete(PermissionInfo record);

    Integer deleteByPrimaryKey(@Param("id") String id);
    
    Integer updateByPrimaryKey(PermissionInfo record);

    List<PermissionInfo> findAll();

    List<PermissionInfo> find(PermissionInfo record);

    Integer getCount(PermissionInfo record);

    PermissionInfo getByPrimaryKey(@Param("id") String id);

	


}
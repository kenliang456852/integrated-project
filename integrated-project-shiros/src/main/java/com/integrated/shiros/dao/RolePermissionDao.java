package com.integrated.shiros.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.integrated.shiros.model.RolePermission;

/**
 * dal Interface:RolePermission
 * @author gencode
 */
public interface RolePermissionDao {

	Integer insert(RolePermission record);

    Integer insertSelective(RolePermission record);
    
    Integer delete(RolePermission record);

    Integer deleteByPrimaryKey(@Param("id") String id);
    
    Integer updateByPrimaryKey(RolePermission record);

    List<RolePermission> findAll();

    List<RolePermission> find(RolePermission record);

    Integer getCount(RolePermission record);

    RolePermission getByPrimaryKey(@Param("id") String id);

	


}
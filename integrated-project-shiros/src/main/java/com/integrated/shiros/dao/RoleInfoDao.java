package com.integrated.shiros.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.integrated.shiros.model.RoleInfo;

/**
 * dal Interface:RoleInfo
 * @author gencode
 */
public interface RoleInfoDao {

	Integer insert(RoleInfo record);

    Integer insertSelective(RoleInfo record);
    
    Integer delete(RoleInfo record);

    Integer deleteByPrimaryKey(@Param("id") String id);
    
    Integer updateByPrimaryKey(RoleInfo record);

    List<RoleInfo> findAll();

    List<RoleInfo> find(RoleInfo record);

    Integer getCount(RoleInfo record);

    RoleInfo getByPrimaryKey(@Param("id") String id);

    List<RoleInfo> getListByAcctId(@Param("acctId") String acctId);

    List<RoleInfo> getListByAcctName(@Param("acctName") String acctName);
}
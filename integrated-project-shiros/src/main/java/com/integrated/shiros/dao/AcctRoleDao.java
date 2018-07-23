package com.integrated.shiros.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.integrated.shiros.model.AcctRole;

/**
 * dal Interface:AcctRole
 * @author gencode
 */
public interface AcctRoleDao {

	Integer insert(AcctRole record);

    Integer insertSelective(AcctRole record);
    
    Integer delete(AcctRole record);

    Integer deleteByPrimaryKey(@Param("id") String id);
    
    Integer updateByPrimaryKey(AcctRole record);

    List<AcctRole> findAll();

    List<AcctRole> find(AcctRole record);

    Integer getCount(AcctRole record);

    AcctRole getByPrimaryKey(@Param("id") String id);

	


}
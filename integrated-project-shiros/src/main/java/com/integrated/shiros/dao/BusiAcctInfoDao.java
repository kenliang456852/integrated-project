package com.integrated.shiros.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.integrated.shiros.model.BusiAcctInfo;

/**
 * dal Interface:BusiAcctInfo
 * @author gencode
 */
public interface BusiAcctInfoDao {

	Integer insert(BusiAcctInfo record);

    Integer insertSelective(BusiAcctInfo record);
    
    Integer delete(BusiAcctInfo record);

    Integer deleteByPrimaryKey(@Param("id") String id);
    
    Integer updateByPrimaryKey(BusiAcctInfo record);

    List<BusiAcctInfo> findAll();

    List<BusiAcctInfo> find(BusiAcctInfo record);

    Integer getCount(BusiAcctInfo record);

    BusiAcctInfo getByPrimaryKey(@Param("id") String id);

	


}
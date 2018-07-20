package com.integrated.shiros.service;

import java.util.List;
import com.integrated.shiros.model.BusiAcctInfo;

/**
 * dal Interface:BusiAcctInfo
 * @author gencode
 * @date 2018-7-21
 */
public interface BusiAcctInfoService {

    public List<BusiAcctInfo> findAll();

    public List<BusiAcctInfo> findBusiAcctInfos(BusiAcctInfo record);

    public BusiAcctInfo getBusiAcctInfoByPrimaryKey(String id);

    public Integer createBusiAcctInfo(BusiAcctInfo record);

    public Integer removeBusiAcctInfo(BusiAcctInfo record);

    public Integer removeByPrimaryKey(String id);

    public Integer modifyBusiAcctInfoByPrimaryKey(BusiAcctInfo record);



}
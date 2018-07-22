package com.integrated.shiros.service.impl;

import com.integrated.shiros.dao.BusiAcctInfoDao;
import com.integrated.shiros.model.BusiAcctInfo;
import com.integrated.shiros.service.BusiAcctInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



/**
 * dal Interface:BusiAcctInfo
 * @author gencode
 * @date 2018-7-21
 */
//@Service
public class BusiAcctInfoServiceImpl implements BusiAcctInfoService{

    @Autowired
    private BusiAcctInfoDao busiAcctInfoDao;

    public List<BusiAcctInfo> findAll(){
       return  busiAcctInfoDao.findAll();
    }

    public List<BusiAcctInfo> findBusiAcctInfos(BusiAcctInfo record){
       return  busiAcctInfoDao.find(record);
    }

    public BusiAcctInfo getBusiAcctInfoByPrimaryKey(String id){
        return busiAcctInfoDao.getByPrimaryKey(id);
    }

    public Integer createBusiAcctInfo(BusiAcctInfo record){
        return busiAcctInfoDao.insert(record);
    }

    public Integer removeBusiAcctInfo(BusiAcctInfo record){
        return busiAcctInfoDao.delete(record);
    }
    
    public Integer removeByPrimaryKey(String id){
    	return busiAcctInfoDao.deleteByPrimaryKey(id);
    }

    public Integer modifyBusiAcctInfoByPrimaryKey(BusiAcctInfo record){
        return busiAcctInfoDao.updateByPrimaryKey(record);
    }









}
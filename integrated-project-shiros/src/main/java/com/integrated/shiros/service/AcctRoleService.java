package com.integrated.shiros.service;

import java.util.List;
import com.integrated.shiros.model.AcctRole;

/**
 * dal Interface:AcctRole
 * @author gencode
 * @date 2018-7-23
 */
public interface AcctRoleService {

    public List<AcctRole> findAll();

    public List<AcctRole> findAcctRoles(AcctRole record);

    public AcctRole getAcctRoleByPrimaryKey(String id);

    public Integer createAcctRole(AcctRole record);

    public Integer removeAcctRole(AcctRole record);

    public Integer removeByPrimaryKey(String id);

    public Integer modifyAcctRoleByPrimaryKey(AcctRole record);



}
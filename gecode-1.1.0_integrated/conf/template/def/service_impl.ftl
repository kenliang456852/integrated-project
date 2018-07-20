package ${package_path}.service.impl;

import java.util.List;
import ${package_path}.service.${class_name}Service;
import ${package_path}.dao.${class_name}Dao;
import ${package_path}.entity.${class_name};

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * dal Interface:${class_name}
 * @author ${author}
 * @date ${sysDate?date}
 */
@Service
public class ${class_name}ServiceImpl implements ${class_name}Service{

    @Autowired
    private ${class_name}Dao ${class_name?uncap_first}Dao;

    public List<${class_name}> findAll(){
       return  ${class_name?uncap_first}Dao.findAll();
    }

    public List<${class_name}> find${class_name}s(${class_name} record){
       return  ${class_name?uncap_first}Dao.find(record);
    }

    public ${class_name} get${class_name}ByPrimaryKey(<#list keysColumn as c>${c.type} ${c.nameJ}<#if c_has_next>,</#if></#list>){
        return ${class_name?uncap_first}Dao.getByPrimaryKey(<#list keysColumn as c>${c.nameJ}<#if c_has_next>,</#if></#list>);
    }

    public Integer create${class_name}(${class_name} record){
        return ${class_name?uncap_first}Dao.insert(record);
    }

    public Integer remove${class_name}(${class_name} record){
        return ${class_name?uncap_first}Dao.delete(record);
    }
    
    public Integer removeByPrimaryKey(<#list keysColumn as c>${c.type} ${c.nameJ}<#if c_has_next>,</#if></#list>){
    	return ${class_name?uncap_first}Dao.deleteByPrimaryKey(<#list keysColumn as c>${c.nameJ}<#if c_has_next>,</#if></#list>);
    }

    public Integer modify${class_name}ByPrimaryKey(${class_name} record){
        return ${class_name?uncap_first}Dao.updateByPrimaryKey(record);
    }









}
package ${package_path}.service;

import java.util.List;
import ${package_path}.entity.${class_name};

/**
 * dal Interface:${class_name}
 * @author ${author}
 * @date ${sysDate?date}
 */
public interface ${class_name}Service {

    public List<${class_name}> findAll();

    public List<${class_name}> find${class_name}s(${class_name} record);

    public ${class_name} get${class_name}ByPrimaryKey(<#list keysColumn as c>${c.type} ${c.nameJ}<#if c_has_next>,</#if></#list>);

    public Integer create${class_name}(${class_name} record);

    public Integer remove${class_name}(${class_name} record);

    public Integer removeByPrimaryKey(<#list keysColumn as c>${c.type} ${c.nameJ}<#if c_has_next>,</#if></#list>);

    public Integer modify${class_name}ByPrimaryKey(${class_name} record);



}
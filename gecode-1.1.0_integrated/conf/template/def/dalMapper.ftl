package ${package_path}.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ${package_path}.model.${class_name};

/**
 * dal Interface:${class_name}
 * @author ${author}
 */
public interface ${class_name}Dao {

	Integer insert(${class_name} record);

    Integer insertSelective(${class_name} record);
    
    Integer delete(${class_name} record);

    Integer deleteByPrimaryKey(<#list keysColumn as c>@Param("${c.nameJ}") ${c.type} ${c.nameJ}<#if c_has_next>,</#if></#list>);
    
    Integer updateByPrimaryKey(${class_name} record);

    List<${class_name}> findAll();

    List<${class_name}> find(${class_name} record);

    Integer getCount(${class_name} record);

    ${class_name} getByPrimaryKey(<#list keysColumn as c>@Param("${c.nameJ}") ${c.type} ${c.nameJ}<#if c_has_next>,</#if></#list>);

	


}
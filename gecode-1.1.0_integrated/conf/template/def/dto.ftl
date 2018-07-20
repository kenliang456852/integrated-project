package ${package_path}.dto;

<#if (hasDateColumn)>
import java.util.Date;
</#if>
<#if (hasBigDecimalColumn)>
import java.math.BigDecimal;
</#if>
import java.io.Serializable;

/**
 * DTO:${class_name}
 * 
 * @author ${author}
 * @date ${sysDate?date}
 */
public class ${class_name}DTO implements Serializable {
	
	private static final long serialVersionUID = ${serialVersionUID}L;
	
	<#list table_column as c>
	private ${c.type}	${c.nameJ};<#if (c.remark?exists && c.remark!="")> /* ${c.remark} */</#if>
	</#list>

	// Constructor
	public ${class_name}DTO() {
	}

	/**
	 * full Constructor
	 */
	public ${class_name}DTO(<#list table_column as c>${c.type} ${c.nameJ}<#if c_has_next>, </#if></#list>) {
		<#list table_column as c>
		this.${c.nameJ} = ${c.nameJ};
		</#list>
	}

	<#list table_column as c>
	public ${c.type} get${c.nameJ?cap_first}() {
		return ${c.nameJ};
	}

	public void set${c.nameJ?cap_first}(${c.type} ${c.nameJ}) {
		this.${c.nameJ} = ${c.nameJ};
	}
	</#list>
	@Override
	public String toString() {
		return "${class_name}DTO [" + <#list table_column as c>"<#if (c_index>=1)>, </#if>${c.nameJ}=" + ${c.nameJ} + </#list> "]";
	}
}

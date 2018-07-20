<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package_path}.dao.${class_name}Dao">

    <resultMap type="${package_path}.model.${class_name}" id="${class_name?uncap_first}ResultMap">
		<#list table_column as c>
		<result property="${c.nameJ}" column="${c.name?upper_case}"  />
		</#list>
	</resultMap>
	
	<sql id="table_columns">
		<#list table_column as c>
		${c.name?upper_case}<#if c_has_next>,</#if>
		</#list>
    </sql>
	<sql id="entity_properties">
		<#list table_column as c>
		${r"#"}{${c.nameJ}}<#if c_has_next>,</#if>
		</#list>
	</sql>

    <!-- columnName like concat('%',#columnName#,'%') -->
    <sql id="page_where">
        <trim prefix="where" prefixOverrides="and | or ">
		<#list table_column as c>
			<#if c.type="String" >
				<if test="${c.nameJ} != null and ${c.nameJ} != ''">and ${c.name?upper_case} = ${r"#"}{${c.nameJ}}</if>
			<#else>
				<if test="${c.nameJ} != null ">and ${c.name?upper_case} = ${r"#"}{${c.nameJ}}</if>
			</#if>
		</#list>
        </trim>
    </sql>

	<insert id="insert" parameterType="${package_path}.model.${class_name}" >
		insert into ${table_name}( <include refid="table_columns" /> ) 
		values ( <include refid="entity_properties" /> )
	</insert>

    <insert id="insertSelective" parameterType="${package_path}.model.${class_name}">
        insert into ${table_name}
        <trim prefix="(" suffix=")" suffixOverrides="," >
        <#list table_column as c>            
            <#if c.type="String" >
				<if test="${c.nameJ} != null and ${c.nameJ} != ''">${c.name?upper_case},</if>
			<#else>
				<if test="${c.nameJ} != null ">${c.name?upper_case},</if>
			</#if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
        <#list table_column as c>
            <#if c.type="String" >
				<if test="${c.nameJ} != null and ${c.nameJ} != ''">${r"#"}{${c.nameJ}},</if>
			<#else>
				<if test="${c.nameJ} != null ">${r"#"}{${c.nameJ}},</if>
			</#if>
        </#list>
        </trim>
	</insert>


	<delete id="delete" parameterType="${package_path}.model.${class_name}">
		delete from ${table_name}
        <include refid="page_where" />
	</delete>

	<delete id="deleteByPrimaryKey">
		delete from ${table_name}
		<trim prefix="where" prefixOverrides="and | or ">
		<#list keysColumn as c>
			<if test="1==1">and ${c.name?upper_case} = ${r"#"}{${c.nameJ}}</if>
		</#list>
        </trim>
	</delete>


	<update id="updateByPrimaryKey" parameterType="${package_path}.model.${class_name}">
		update ${table_name} 
		<trim prefix="set" suffixOverrides=",">
		<#list notKeysColumn as c>
			<#if c.type="String" >
				<if test="${c.nameJ} != null and ${c.nameJ} != ''">${c.name?upper_case} = ${r"#"}{${c.nameJ}},</if>
			<#else>
				<if test="${c.nameJ} != null ">${c.name?upper_case} = ${r"#"}{${c.nameJ}},</if>
			</#if>
		</#list>
		</trim>
		<trim prefix="where" prefixOverrides="and | or ">
		<#list keysColumn as c>
			<if test="1==1">and ${c.name?upper_case} = ${r"#"}{${c.nameJ}}</if>
		</#list>
        </trim>
	</update>

    <select id="findAll" resultMap="${class_name?uncap_first}ResultMap">
        select <include refid="table_columns" />
        from ${table_name}
    </select>

    <select id="find" resultMap="${class_name?uncap_first}ResultMap">
        select <include refid="table_columns" />
        from ${table_name}
        <include refid="page_where" />
	</select>

    <select id="getCount" resultType="int" >
        select count(*) from ${table_name}
        <include refid="page_where" />
    </select>


    <select id="getByPrimaryKey" resultMap="${class_name?uncap_first}ResultMap"  >
		select <include refid="table_columns" />
		from ${table_name}
		<trim prefix="where" prefixOverrides="and | or ">
		<#list keysColumn as c>
			<if test="1==1">and ${c.name?upper_case} = ${r"#"}{${c.nameJ}}</if>
		</#list>
        </trim>
	</select>
	
	<!-- other SQL -->


</mapper>
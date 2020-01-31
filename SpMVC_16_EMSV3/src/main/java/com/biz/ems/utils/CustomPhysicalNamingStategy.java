package com.biz.ems.utils;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/*
 * hibernate와 mybatis를 같이 쓰려고 하는 상황에
 * 변수명 snake case와 camel case가 겹쳐서 오류가 나는 상황을 해결하려고 만든 class
 */
public class CustomPhysicalNamingStategy implements PhysicalNamingStrategy{

	@Override
	public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return convertToSnakeCase(name);
	}

	@Override
	public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return convertToSnakeCase(name);
	}

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return convertToSnakeCase(name);
	}

	@Override
	public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return convertToSnakeCase(name);
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return convertToSnakeCase(name);
	}

	public Identifier convertToSnakeCase(Identifier identifier) {
		
		if(identifier == null) {
			return identifier;
		}
		
		String regex = "([a-z])([A-Z])";
		String replacement = "$1_$1";
		
		String newName = identifier.getText().replaceAll(regex, replacement).toLowerCase();
		
		return Identifier.toIdentifier(newName);
		
		
	}
	
	
}

package com.nightsoul.commons.daosupport.mybatis;

import java.lang.reflect.ParameterizedType;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.FactoryBean;


public abstract class MapperDaoSupport <T> implements FactoryBean<T> {
	private Class<T> daoClass;
	
	@Resource
	private SqlSessionFactory sqlSessionFactory;
	private MapperFactoryBean<T> mapperFactoryBean;
	
	@SuppressWarnings("unchecked")
	public MapperDaoSupport() {
		ParameterizedType paramedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		daoClass = (Class<T>) paramedType.getActualTypeArguments()[0];
	}
	
	@PostConstruct
	public void postConstruct() throws Exception {
		mapperFactoryBean = new MapperFactoryBean<T>();
		mapperFactoryBean.setMapperInterface(daoClass);
		mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public T getObject() throws Exception {
		return mapperFactoryBean.getObject();
	}

	@Override
	public Class<?> getObjectType() {
		return daoClass;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}

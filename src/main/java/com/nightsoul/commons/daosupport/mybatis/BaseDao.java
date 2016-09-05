package com.nightsoul.commons.daosupport.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nightsoul.commons.paging.PagingParam;


public interface BaseDao <T> {
	
	T getById(Integer id);
	
	List<T> getByIds(Integer[] ids);
	
	List<T> getAll();
	
	int save(T t);
	
	int update(T t);
	
	int deleteById(Integer id);
	
	int deleteByIds(@Param("ids")Integer[] ids);
	
	List<T> query(T t);

	List<T> queryByPaging(PagingParam<T> pagingParam);
	
	long getCount();

	long getQueryByPagingCount(T t);
	
	List<T> getByPaging(@Param("limit")int limit, @Param("offset")int offset);
	
	Integer getLastInsertId();
}

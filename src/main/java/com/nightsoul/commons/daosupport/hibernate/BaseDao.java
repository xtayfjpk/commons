package com.nightsoul.commons.daosupport.hibernate;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import com.nightsoul.commons.paging.QueryResult;

/**
 * CRUD父接口
 * @author zj
 *
 * @param <T>
 */
public interface BaseDao <T> {
	/**
	 * 保存实体
	 * @param entity
	 */
	public void save(T  entity);
	
	
	/**
	 * 更新实体
	 * @param entity
	 */
	public void update(T entity);
	
	
	/**
	 * 删除实体
	 * @param entityid
	 */
	public void delete(Serializable entityId);
	
	
	/**
	 * 删除实体
	 * @param entityid  实体id数组
	 */
	public void delete(Serializable[]  entityIds);
	
	
	/**
	 * 获取实体，如果entityId为null，则返回null，不会抛异常
	 * @param entityClass  实体类
	 * @param entityId  实体id
	 * @return
	 */
	public T  find(Serializable entityId);
	
	/**
	 * 获取id数据中的所有实体，如果entityIds为null或者entityIds.length==0则返回一个emptyList
	 * @param entityClass   实体类
	 * @param entityIds  实体id数组
	 * @return
	 */
	public List<T>  find(Serializable[] entityIds);
	
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<T>  findAll();
	
	
	
	/**
	 * 获取分页数据，附加查询参数并且排序  如果firstIndex与maxResult中有一个值为-1则不分页
	 * @param firstIndex  开始索引
	 * @param maxResult  需要获取的记录数
	 * @param orderBy LinkedHashMap中： key为实体的属性，值为升序(asc)或降序(desc)，不区分大小写
	 * @return
	 */
	public QueryResult<T>  getScrollData(int firstIndex, int maxResult, String whereHql , Object[] queryParams  , LinkedHashMap<String, String>  orderBy);
	
	
	/**
	 * 需要查询参数但不排序
	 * @param firstIndex
	 * @param maxResult
	 * @param wherejpql
	 * @param queryParams
	 * @return
	 */
	public QueryResult<T>  getScrollData(int firstIndex, int maxResult, String whereHql , Object[] queryParams  );
	
	
	/**
	 * 排序但不需要查询参数
	 * @param firstIndex
	 * @param maxResult
	 * @param orderBy
	 * @return
	 */
	public QueryResult<T>  getScrollData(int firstIndex, int maxResult,  LinkedHashMap<String, String>  orderBy);
	
	
	/**
	 *只需要分页，不需要查询参数和排序
	 * @param firstIndex
	 * @param maxResult
	 * @return
	 */
	public  QueryResult<T>  getScrollData(int firstIndex, int maxResult);
	
	
	/**
	 * 只是获取全部数据
	 * @param entityClass
	 * @return
	 */
	public QueryResult<T>  getScrollData();
	
	/**
	 * 获取指定条件的所有记录但不排序
	 * @param wherejpql
	 * @param queryParams
	 * @return
	 */
	public QueryResult<T>  getScrollData(String wherejpql , Object[] queryParams);
	
	/**
	 * 获取指定条件的所有记录而且排序
	 * @param wherejpql
	 * @param queryParams
	 * @param orderBy
	 * @return
	 */
	public QueryResult<T>  getScrollData(String wherejpql , Object[] queryParams, LinkedHashMap<String, String>  orderBy);
	
	/**
	 * 获取指定字段指定范围内的所有数据
	 * @param fieldName
	 * @param scopeValues
	 * @return
	 */
	public   List<T>  getScopeData(String  fieldName, Object[]  scopeValues);
	
	/**
	 * 清除一级缓存的数据
	 */
	public  void  clear();
	
	
	/**
	 * 获取实体的总记录数
	 * @return  总记录数
	 */
	public long getCount();
	
	/**
	 * 获取指定条件下的总记录数
	 * @param entityClass
	 * @param wherejpql  where语句
	 * @param queryParams  查询参数
	 * @return
	 */
	public long  getCount(String  wherejpql, Object[] queryParams);
}

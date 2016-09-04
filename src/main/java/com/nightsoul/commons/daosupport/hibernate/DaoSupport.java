package com.nightsoul.commons.daosupport.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nightsoul.commons.paging.QueryResult;


/**
 * CRUD实现类
 * @author zj
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class DaoSupport<T> implements BaseDao<T> {
	@Resource(name="sessionFactory")
	protected SessionFactory sessionFactory;
	private Class<?> entityClass = null;
	public DaoSupport() {
		ParameterizedType pType =  (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<?> clazz = (Class<?>) pType.getActualTypeArguments()[0];
		this.entityClass = clazz;
	}

	public void save(T entity) {
		currentSession().save(entity);
	}

	public void update(T entity) {
		currentSession().merge(entity);
	}

	public void delete(Serializable entityId) {
		delete(new Serializable[]{entityId});
	}

	public void delete(Serializable[] entityIds) {
		if(null!=entityIds && entityIds.length>0) {
			Session session = currentSession();;
			for(Serializable entityId : entityIds) {
				session.delete(session.load(entityClass, entityId));
			}
		}
	}

	public T find(Serializable entityId) {
		if(null==entityId) {
			return null;
		}
		return (T) currentSession().get(entityClass, entityId);
	}


	
	public List<T>  find(Serializable[] entityIds) {
		if(null==entityIds || 0==entityIds.length) {
			return Collections.EMPTY_LIST;
		}
		StringBuilder  inStr = new  StringBuilder();
		for(int i=0; i<entityIds.length; i++) {
			inStr.append('?').append(',');
		}
		inStr.deleteCharAt(inStr.length()-1);
		String  queryStr = "SELECT o FROM  "+getEntityName()+" o  WHERE  o.id  IN("+inStr+")";
		Query query = currentSession().createQuery(queryStr);
		for(int i=0; i<entityIds.length; i++) {
			query.setParameter(i, entityIds[i]);
		}
		return query.list();
	}
	
	public List<T> findAll() {
		String  queryStr = "SELECT o FROM "+getEntityName()+" o";
		return currentSession().createQuery(queryStr).list();
	}
	
	public  List<T>  getScopeData(String  fieldName, Object[]  scopeValues) {
		String  queryStr = "SELECT  o  FROM  "+getEntityName()+"  o  WHERE  o."+fieldName+"  in("+buildScopes(scopeValues)+")";
		Query query = currentSession().createQuery(queryStr);
		for(int i=0; i<scopeValues.length; i++) {
			query.setParameter(i, scopeValues[i]);
		}
		return query.list();
	}
	
	private String buildScopes(Object[] scopes) {
		StringBuilder  scopeStr = new  StringBuilder();
		for(int i=0; i<scopes.length; i++) {
			scopeStr.append('?').append(',');
		}
		return scopeStr.deleteCharAt(scopeStr.length()-1).toString();
	}

	public QueryResult<T> getScrollData(int firstIndex, int maxResult, String whereHql, Object[] queryParams,
			LinkedHashMap<String, String> orderBy) {
		String entityName=this.getEntityName();
		
		QueryResult<T>  qr=new QueryResult<T>();
		Query query=currentSession().createQuery("SELECT o FROM   "+entityName+ "   o "+(whereHql==null  ?  ""  : " WHERE " + whereHql)+buildOrderBy(orderBy));
		this.setQueryParmas(query, queryParams);
		if(-1!=firstIndex  &&  -1!=maxResult) {
			query.setFirstResult(firstIndex);
			query.setMaxResults(maxResult);
		}
		qr.setResultList(query.list());
		
		query=currentSession().createQuery("SELECT COUNT("  + getCountField()+  ") FROM   "+entityName +"  o "+(whereHql==null  ?  " "  : " where " +  whereHql));
		this.setQueryParmas(query, queryParams);
		qr.setTotalRecord((Long)query.uniqueResult());
		return qr;
	}
	
	/**
	 * 获取实体名称
	 * @param entityClass
	 * @return
	 */
	protected String getEntityName() {
		return this.sessionFactory.getClassMetadata(this.entityClass).getEntityName();
		//return entityClass.getSimpleName();
	}
	/**
	 * 组装order by语句
	 * @param orderBy
	 * @return
	 */
	protected String buildOrderBy(LinkedHashMap<String, String>  orderBy) {
		StringBuilder orderByql=new StringBuilder("");
		if(orderBy!=null  &&  orderBy.size()>0) {
			orderByql.append("  order by  ");
			for(String  key : orderBy.keySet()) {
				orderByql.append("o.").append(key).append("  ").append(orderBy.get(key).toUpperCase()).append(" ,");
			}
			orderByql.deleteCharAt(orderByql.length()-1);
		}
		return orderByql.toString();
	}
	/**
	 * 设定查询参数
	 * @param query
	 * @param queryParams
	 */
	protected void setQueryParmas(Query query,  Object[]  queryParams) {
		if(queryParams!=null  &&  queryParams.length>0) {
			for(int i=0; i<queryParams.length;  i++) {
				query.setParameter(i, queryParams[i]);
			}
		}
	}

	public QueryResult<T> getScrollData(int firstIndex, int maxResult, String whereHql, Object[] queryParams)	{
		return this.getScrollData(firstIndex, maxResult, whereHql, queryParams, null);
	}


	public  QueryResult<T> getScrollData(int firstIndex, int maxResult,	LinkedHashMap<String, String> orderBy) {
		return this.getScrollData(firstIndex, maxResult, null, null, orderBy);
	}


	public QueryResult<T> getScrollData(int firstIndex, int maxResult)	{
		return this.getScrollData(firstIndex, maxResult, null, null, null);
	}


	public QueryResult<T> getScrollData()	{
		return this.getScrollData(-1, -1);
	}
	
	public QueryResult<T> getScrollData(String whereHql, Object[] queryParams) {
		return  this.getScrollData(-1, -1, whereHql, queryParams);
	}
	
	public QueryResult<T> getScrollData(String whereHql, Object[] queryParams, LinkedHashMap<String, String> orderBy) {
		return  this.getScrollData(-1, -1, whereHql, queryParams, orderBy);
	}
	
	public  void  clear()	{
		currentSession().clear();
	}
	
	public long getCount()  {
		return  getCount(null, null);
	}
	
	public long  getCount(String  whereHql, Object[] queryParams) {
		Query  query =  currentSession().createQuery("SELECT  COUNT( "   +  getCountField()+  ")  from "+this.getEntityName()+" o  "+(whereHql==null  ?  ""  :  " where "+whereHql));
		if(null!=whereHql &&  queryParams.length>0) {
			for(int i=0; i<queryParams.length; i++) {
				query.setParameter(i, queryParams[i]);
			}
		}
		return  (Long) query.uniqueResult();
	}
	
	
	/**
	 * 获取统计属性,该方法是为了解决hibernate解析联合主键select count(o) from Xxx o语句BUG而增加,hibernate对此jpql解析后的sql为select count(field1,field2,...),显示使用count()统计多个字段是错误的
	 * @param <E>
	 * @param clazz
	 * @return
	 */
	/*protected String getCountField()  {
		String out = "o";
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(entityClass).getPropertyDescriptors();
			for(PropertyDescriptor propertydesc : propertyDescriptors) {
				Method method = propertydesc.getReadMethod();
				if(method!=null && method.isAnnotationPresent(EmbeddedId.class))	{				
					PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
					out = "o."+ propertydesc.getName()+ "." + (!ps[1].getName().equals("class")? ps[1].getName(): ps[0].getName());
					break;
				}
			}
		} catch (Exception e) 	{
			e.printStackTrace();
		}
        return out;
	}*/

	protected String getCountField()  {
		return "select count(o) from " +this.getEntityName()+ " o";
	}
	
	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

}

package com.cnu.dao.core;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.cnu.annotation.Column;
import com.cnu.annotation.Modify;
import com.cnu.annotation.Table;
import com.cnu.page.PageDiv;





public class BaseDaoImp<T extends Serializable> implements BaseDao<T> {
    private static final Logger log=Logger.getLogger(BaseDaoImp.class);
	public static ConnectionManager cm=ConnectionManager.getInstance();
	@Override
	public int updateBySql(String sql, Object... param) throws SQLException 
	{
		int result=0;
		PreparedStatement ps=cm.getConnection().prepareStatement(sql);
		if(null!=param&&param.length>0)
		{
			for(int i=0;i<param.length;i++)
			{
				ps.setObject(i+1, param[i]);
			}
		}
		result=ps.executeUpdate();
		ps.close();
		return result;
	}

	@Override
	public int[] executeBatch(String sql, Object[]... params)
			throws SQLException 
	{
		int []result=null;
		PreparedStatement ps=cm.getConnection().prepareStatement(sql);
		if(null!=params&&params.length>0)
		{
			for(int i=0;i<params.length;i++)
			{
				Object[]param=params[i];
				if(null!=param&&param.length>0)
				{
					for(int j=0;j<param.length;j++)
					{
					ps.setObject(j+1,param[j]);
					}
					ps.addBatch();
				}
			}
		}
		result=ps.executeBatch();
        ps.close();
		return result;
	}

	@Override
	public int[] addBatch(String[] sql) throws SQLException {
	
		int []result=null;
		Statement stat=cm.getConnection().createStatement();
		
		if(null!=sql&&sql.length>0)
		{
			for(String tem:sql)
			{
				stat.addBatch(tem);
			}
			result=stat.executeBatch();
		}
		return result;
	}

	@Override
	public int add(T t) throws SQLException 
	{
		int re=-1;
		StringBuilder strFied=new StringBuilder();//所有字段的名字
		StringBuilder strQuery=new StringBuilder();//?,?,?
		//第一步，将t的每个字段取出来，拼sql语句
        // bean的class
		
		//分析表名
		String tableName=getTableName(t);
		//分析表名完成
		//分析字段
		//key是字体内名,object是这个字段的值
		TreeMap<String,Object> fields=parseFields(t);
		
		parseSQL(fields, new StringBuilder[]{strFied,strQuery},  new String[]{"{0}","?"});
		String sqltem="insert into "+tableName+"("+strFied.toString()+") values("+strQuery+")";
		executeSQL(fields,sqltem);
		//得到最后插入的id
		Statement stat=cm.getConnection().createStatement();
		ResultSet rs=stat.executeQuery("select LAST_INSERT_ID()");
		System.out.println("jhhhhhkjjj");
	    if(rs.next())re=rs.getInt(1);
		rs.close();
		stat.close();
	
	    log.info(sqltem);//日志加入sql
	    System.out.println("kkkkkkf");
	   return re;
	}
		

	@Override
	public void update(T t) throws SQLException 
	{
		//修改t中id的记录,把t中的每一项同步到数据
		//String sql="update table_name set f1=?,f2=?,f3=? where id=?";
		TreeMap<String,Object> fields=parseFields(t);
		String tableName=getTableName(t);
		StringBuilder strField=new StringBuilder();
		parseSQL(fields, new StringBuilder[]{strField}, new String[]{"{0}=?"});
		String sql="update "+tableName+" set "+strField.toString()+" where id="+getIdByBean(t);
        //log.debug(sql);
		if(sql.toLowerCase().endsWith("null"))
		{
			log.error("更新数据不能没有ID");
			throw new SQLException("没有id");
		}else
		{
			executeSQL(fields,sql);
			log.info(sql);//日志加入sql
		}
	}

	@Override
	public void delete(Class<T> clazz,Integer id) throws SQLException 
	{
		T t=null;
		 try
		{
			t=(T)clazz.newInstance();
		} catch (Exception e)
		{
			log.error("010_BaseDaoImp_删除实体对象失败"+e);
		} 
		
		
		String tableName=getTableName(t);
		if(null!=id&&id>0)
		{
			String sql="delete from "+tableName+" where id=?";
			this.updateBySql(sql,new Object[]{id});
			log.info(sql);
		}else
		{
			log.error("011_BaseDaoImp_删除数据不能没有ID");
			throw new SQLException("没有id");
		}
	}

	@Override
	public T getById(Class<T> clazz,T tem, Integer id) throws SQLException 
	{
		
		
		 try
		{
			 if(null==tem)
			   tem=(T)clazz.newInstance();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//T t=null;
		String tableName=getTableName(tem);
		String sql="select * from "+tableName+" where id="+id;
		Statement stat=cm.getConnection().createStatement();
		ResultSet rs=stat.executeQuery(sql);
		if(rs.next())
		{
			//将rs转为一个t对象
			tem=parseResultSetToBean(clazz,rs);
		}
		rs.close();
		stat.close();
		return tem;
	}

	@Override
	public List<T> getAll(Class<T> clazz, String sql, Object... params)
			throws SQLException 
	{
		
		List<T> list=new ArrayList<T>();
		PreparedStatement ps=cm.getConnection().prepareStatement(sql);
		setParamsToPs(ps,params);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			list.add(parseResultSetToBean(clazz,rs));
		}
		return list;
	}

/*	@Override
	public PageDiv<T> getByPage(Class<T> clazz, int pageNo, int PageSize,
			Finder[] find, Order[] order) throws SQLException 
	{
		return null;
	}*/

	@Override
	public int getTotalCount(String sql,Object...params) throws SQLException 
	{
		int re=0;
		if(sql.toLowerCase().startsWith("select count(*)"))
		{
			PreparedStatement ps=cm.getConnection().prepareStatement(sql);
			setParamsToPs(ps,params);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				re=rs.getInt(1);
			}
			rs.close();
			ps.close();
		}
		
		return re;
	}
	
	

	@Override
	public T getByUniq(Class<T> clazz, String sql, Object... params)
			throws SQLException 
	{
		T t=null;
		PreparedStatement ps=cm.getConnection().prepareStatement(sql);
		setParamsToPs(ps, params);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			t=parseResultSetToBean(clazz,rs);
		}
		
		return t;
	}

	@Override
	public List<T> getByTop(Class<T> clazz, String sql, int top,
			Object... params) throws SQLException 
	{
		
		List<T> list=new ArrayList<T>();
		PreparedStatement ps=cm.getConnection().prepareStatement(sql+" limit "+top);
		setParamsToPs(ps,params);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			list.add(parseResultSetToBean(clazz,rs));
		}
		rs.close();
		ps.close();
		
		return list;
	}

	@Override
	public PageDiv<T> getByPage(Class<T> clazz, String sql, int pageNo,
			int pageSize, Object... params) throws SQLException 
	{
		List<T> list=new ArrayList<T>();
		PreparedStatement ps=cm.getConnection().prepareStatement(sql+" limit "+(pageNo-1)*pageSize+","+pageSize);
		setParamsToPs(ps,params);
		ResultSet rs=ps.executeQuery();
    	while(rs.next())
		{
			list.add(parseResultSetToBean(clazz,rs));
		}
		// select * from article where .....
		
		int index=sql.toLowerCase().indexOf("from");
		String countsql="select count(*) "+sql.substring(index);

		//rs.close();
		//ps.close();
		
		return new PageDiv<T>(pageNo, pageSize, this.getTotalCount(countsql,params), list);
	}

	
	/**
	 * 
	 * 要用到的一些方法
	 * 
	 */
	
	/*-----------------一些工具类---------------*/
	/**
	 * 通过对象得到对应的表名
	 */

	public String getTableName(T t)
	{
		
		String tableName=null;
		/*
		if(User.class==t.getClass()||Friends.class==t.getClass())
		{
			try
			{
				System.out.println(t.getClass());
				System.out.println("22222.");
				Field divtable=t.getClass().getDeclaredField("tableName");
				System.out.println(divtable);
				System.out.println("3333123");
				divtable.setAccessible(true);
				System.out.println("3333");
				tableName=(String)divtable.get(t);
				//System.out.println();
				System.out.println("......");
			} catch (Exception e)
			{
				log.error("012_BaseDaoImp_获取实体类中的表名出错"+e);
			}
			
		}else
		{*/
			//Class clazz=t.getClass();
			//分析表名
			Object tclazz=t.getClass().getAnnotation(Table.class);
			//验证实体bean上有没有配置表名
			if(null!=tclazz&&tclazz instanceof Table)
			{
				//有配置表名
				Table table=(Table)tclazz;
				tableName=table.value();
			}else
			{
				//没有配置表名
				tableName=t.getClass().getName().substring(t.getClass().getName().lastIndexOf('.')+1).toLowerCase();
			}
		
		return tableName;
	}
	
	
	/**
	 * 将对象的值和属性解析到一个map中 
	 * @param t
	 * @return
	 */
	public TreeMap<String,Object> parseFields(T t)
	{
		TreeMap<String,Object> fields=new TreeMap<String,Object>();
		Field [] allFields=t.getClass().getDeclaredFields();//得到所有的成员
		//if(null==allFields)return;//如果这个实体bean没有字段
		for(Field tem:allFields)
		{
			String fname=tem.getName();//得到字段名
			tem.setAccessible(true);   //把私有属性设为可写
			//读modify配置，如果有，并且add=false，那就不加
			Object modobj=tem.getAnnotation(Modify.class);
			if(null!=modobj&&modobj instanceof Modify)
			{
				//排除
				continue;
			}
			//判断是不是id
			if(!("id").equals(fname)&&!"serialVersionUID".equals(fname))
			{
					  //判断成员上有没有anotation
				  Object fano=tem.getAnnotation(Column.class);
				
				  try {
					if(null!=fano&&fano instanceof Column)
					  {
						  //说明有annotation
						  Column co=(Column)fano;
						  fields.put(co.value(),tem.get(t));
					  }else
					  {
						  //没有annotaion
						  fields.put(fname,tem.get(t));
					  }
				} catch (Exception e) {
					log.error("013_BaseDaoImp_取值出错"+e);
				} 
			}
		}
		return fields;
	}
/**
 * 用来拼sql语句
 * @param fields  map中存放了对象属性和对应的值
 * @param sb  要拼的字符串
 * @param pattern  模版
 */
	public void  parseSQL(TreeMap<String,Object> fields,StringBuilder[]sb,String[] pattern)
	{
		Set<String> set=fields.keySet();
		Iterator<String> it=set.iterator();
	    int count=set.size();
	    int index=0;
	    while(it.hasNext())
	    {
	    	String key=it.next();
	    	index++;
	    	if(index!=count)
	    	{
	    		for(int i=0;i<sb.length;i++)
	    		{
	    			sb[i].append(MessageFormat.format(pattern[i], key));
	    			sb[i].append(",");
	    		}
	    	//	strFied.append(key+",");
			//	strQuery.append("?,");
	    	}else
	    	{
	    	//	strFied.append(key);
			//	strQuery.append("?");
	    		for(int i=0;i<sb.length;i++)
	    		{
	    			sb[i].append(MessageFormat.format(pattern[i], key));
	    		}
	    	}
	 
	    }
		
	}
	
	/**
	 * 通过一个对象，来得到id
	 * @param t
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer getIdByBean(T t)
	{
		Integer id=null;
		Class clazz=t.getClass();
		try {
			Field fid=clazz.getDeclaredField("id");
			fid.setAccessible(true);
			if(null!=fid)id=(Integer)fid.get(t);

		} catch (Exception e) {
			log.error("0014_BaseDaoImp_从Bean中解析id出错"+e);
		} 
		
		
		return id;
	}
	/**
	 * 合并sql语句，并执行
	 * @param fields
	 * @param sql
	 * @throws SQLException
	 */
	public void executeSQL(TreeMap<String,Object> fields,String sql)throws SQLException
	{
		PreparedStatement ps=cm.getConnection().prepareStatement(sql);
	    Iterator<String> it1=fields.keySet().iterator();
	   	int paramindex=1;
		while(it1.hasNext())
		{
			//NULL时会出问题
			ps.setObject(paramindex++,fields.get(it1.next()));

		}
		
	    ps.executeUpdate();
	    ps.close();
	}
	
	//转换数据表字段为对象属性
	public String parseColumnToField(String column)
	{
		
		if(null!=column&&column.indexOf("_")==-1)
		{
			return column;
		}else
		{
			int index=column.indexOf("_");
			return column.substring(0, index)+column.substring(index+1,index+2).toUpperCase()+column.substring(index+2);
		}
		
	}
	
	/**
	 * 将rs中的当前记录，转为一个对象
	 * @param clazz   对象的类型 
	 * @param rs      resultset
	 * @return
	 */
	public T parseResultSetToBean(Class<T> clazz,ResultSet rs)
	{
		T t=null;
		try {
			t = (T)clazz.newInstance();
			ResultSetMetaData  rmd=rs.getMetaData();
			int columnCount=rmd.getColumnCount();
			//放每个字段的名字
			String []fieldsName=new String[columnCount];
			for(int i=0;i<columnCount;i++)
			{
				fieldsName[i]=rmd.getColumnLabel(i+1);
			}
			for(int i=0;i<columnCount;i++)
			{
				int columnType=rmd.getColumnType(i+1);
				switch (columnType) {
				case Types.INTEGER:
				case Types.BIGINT:
				case Types.TINYINT:
					setValueToField(clazz,t,rs.getInt(i+1),fieldsName[i]);
					break;
				case Types.VARCHAR:
				case Types.VARBINARY:
				case Types.LONGVARCHAR:
				case Types.CHAR:
					setValueToField(clazz,t,rs.getString(i+1),fieldsName[i]);
				    break;
				case Types.DATE:
				case Types.TIMESTAMP:
				   setValueToField(clazz,t,rs.getTimestamp(i+1),fieldsName[i]);
				   break;
				default:
					break;
				}
			}
		} catch (Exception e) {
		 log.error("015_BaseDaoImp_将ResultSet转换为ben出错"+e);
		}
		return t;
	}
	/**
	 * 给指字的对象字段赋值，值来自于rs
	 * @param clazz  Bean的类型
	 * @param t      要赋值的对象
	 * @param value  值 
	 * @param fieldName  字段名
	 */
    @SuppressWarnings("rawtypes")
	public void setValueToField(Class clazz,T t,Object value,String fieldName)
    {
    	try {
			String filedName=parseColumnToField(fieldName);
			
			Field field=clazz.getDeclaredField(filedName);
			if(null!=field)
			{
			field.setAccessible(true);
			field.set(t, value);
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
	 * 给ps设参数
	 * @param ps
	 * @param params
	 * @throws SQLException
	 */
	public void setParamsToPs(PreparedStatement ps,Object...params) throws SQLException
	{
		if(null!=params&&params.length>0)
		{
			for(int i=0;i<params.length;i++)
			{
			ps.setObject(i+1,params[i]);
			}
		}
	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String sql, Object... params) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer executeQueryForCount(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T executeQueryForBean(String sql, Class<T> clazz)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T executeQueryForBean(String sql, Class<T> clazz, Object... params)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> executeQuery(String sql, Class<T> clazz) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> executeQuery(String sql, Class<T> clazz, Object... params)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> executeRawQuery(String sql, Object... params)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}

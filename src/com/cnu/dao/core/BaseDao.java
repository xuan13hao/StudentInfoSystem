package com.cnu.dao.core;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



import com.cnu.page.PageDiv;
/**
 * 封装操作数据的基本方法，操作的是对象
 * @param <T>
 */
public interface BaseDao<T extends Serializable>
{
	/**
	 * 通过sql语句,进行insert update delete create 等操作
	 * insert into stu(name,age ) values(?,?);  'zhangsan' 18
	 * @param sql
	 * @param param
	 * @return
	 */
	public int updateBySql(String sql,Object...param)throws SQLException;
	/**
	 * 批量执行更新修改操作，
	 * delete from admin where id=? and name=?
	 * @param sql 用于PreparedStatement的SQL语句
	 * @param params 二维对象数组，每行是一条SQL语句的参数。
	 * @return 执行完影响的记录数
	 */
	public int[] executeBatch(String sql,Object[]...params)throws SQLException; 
	/**
	 * 批处理sql语句，组装多条语句，一起发给数据库
	 * set autocommit=0
	
	 * commit | ROLLBACK
	 * @param sql
	 * @return
	 */
	public int[] addBatch(String[]sql)throws SQLException;
	

	/**
	 * 增加一个对象，将t对像的所有成员构造sql语句，插入数据表
	 * @param t  和表对应的bean
	 * @retuen 最后加入数据的id值
	 * @throws SQLException
	 */
	public int add(T t)throws SQLException;
	/**
	 * 修改一个对象，根据t对像的id,所以t的id不能为空
	 * @param t
	 * @throws SQLException
	 */
	public void update(T t)throws SQLException;
	/**
	 * 根据t中的id删除对应数据
	 * @param t
	 * @throws SQLException
	 */
	public void delete(Class<T> clazz,Integer id)throws SQLException;
	/**
	 * 通过id得到一个对象
	 * @param clazz
	 * @return
	 * @throws SQLException
	 */
	public T getById(Class<T> clazz,T tem,Integer id)throws SQLException; 
	/**
	 * 得到指定类的所有数据
	 * @param clazz
	 * @param sql
	 * @param params Null
	 * @return
	 * @throws SQLException
	 */
	public List<T> getAll(Class<T> clazz,String sql,Object...params)throws SQLException;
	/**
	 * 得到分页数据
	 * @param clazz  查找表对应的bean
	 * @param pageNo  当前页码
	 * @param PageSize  //每页条数
	 * @return
	 */
   // public PageDiv<T> getByPage(Class<T> clazz,int pageNo,int PageSize,Finder[] find,Order[] order)throws SQLException;
    /**
     * 得到总条数
     * @param clazz
     * @param sql
     * @return
     */
    public int getTotalCount(String sql,Object...params)throws SQLException;
    
    
    /**
     * 通过sql语句找第一条数据
     * @param clazz 目标对象的clazz
     * @param sql   select * from aa;
     * @param param 预处理参数，可以为null
     * @return  对应的实体对象
     */
    public T getByUniq(Class<T> clazz,String sql,Object...params)throws SQLException;
    /**
     * 找出前几条数据
     * @param clazz
     * @param sql
     * @param 要几条数据
     * @param params
     * @return
     */
    public List<T> getByTop(Class<T> clazz,String sql,int top,Object...params)throws SQLException;
    /**
     * 通过sql语句分页找数据
     * @param clazz
     * @param sql
     * @param pageNo
     * @param pageSize
     * @param params
     * @return
     */
    public PageDiv<T> getByPage(Class<T> clazz,String sql,int pageNo,int pageSize,Object...params)throws SQLException; 
    
    
    

	public Connection getConnection();
	/** 
	 * 批量执行sql语句，将一条sql重复好多次
	 * demo:delete from t_name where id=? and xx=?
	 * @param sql   要批量执行的sql语句
	 * @param params   传入的参数为二维数组
	 * @return 一维数据，为每条sql语句影响的数据表条数
	 */
	public int[] batch(String sql,Object[][] params)throws SQLException;
	
	/**
     * 执行sql语句insert update delete
     * @param sql
     * @return  返回影响数据表的条数
     */
    public int executeUpdate(String sql)throws SQLException;
	
    /**
     * 更新数据表inser update delete
     * demo:insert  t_name(aa,aa,ss) values(?,?,?) 
     * @param sql 
     * @param param  加入的参数
     * @return 返回影响数据表的条数
     */
    public int executeUpdate(String sql,Object...params)throws SQLException;
	/**
	 * 查句指定sql所查找的记录总数  sql语数一定要以selec count(*)开始
	 * @param sql  sql语句select count(id) from user;
	 * @return   返回记录总条数
	 */
    public Integer executeQueryForCount(String sql)throws SQLException;
    /**
     * 查询一条记录，如果返回多条记录，则返回第一条
     * @param sql  sql语句select * from user where id=12
     * @return  返回一条记录的对象封装
     */
    public T executeQueryForBean(String sql, Class<T> clazz)throws SQLException;
    /**
     * 查询一条记录，如果返回多条记录，则返回第一条
     * @param sql  sql语句select * from user where id=?
     * @param params  用于替代?的参数
     * @return  返回一条记录的对象封装
     */
    public T executeQueryForBean(String sql, Class<T> clazz,Object...params)throws SQLException;

    
    /**
     * 查询数据表，将结果转为一个List
     * String sql=select * from user;
     * List<User> list=executeQuery(sql,User.class);
     * @param sql   要执行的sql语够大
     * @param clazz  返回list中元素的类型
     * @return   返回数据表结果的list封装
     */
    public List<T> executeQuery(String sql,Class<T> clazz)throws SQLException; 
    /**
     * 查询数据表，将结果转为一个List
     * String sql=select * from user where id=?;
     * List<User> list=executeQuery(sql,User.class);
     * @param sql   要执行的sql语够大
     * @param clazz  返回list中元素的类型
     * @param param  为传入的参数用来封替代?
     * @return   返回数据表结果的list封装
     */
    public List<T> executeQuery(String sql,Class<T> clazz,Object...params)throws SQLException;
    /**
     * 查询表，将每条结果封装为Object[] 投影查询，用于查找一个表的部分数据
     * select username,password from user;
     * @param sql
     * @param param
     * @return
     */
    public List<Object[]> executeRawQuery(String sql,Object...params)throws SQLException;
		
	/**
	 * apache DButil
	 * Execute an SQL SELECT query without any replacement parameters. The caller is responsible for closing the connection. 
	 * @param sql
	 * @param rsh
	 * @return
	 */
	
    
}

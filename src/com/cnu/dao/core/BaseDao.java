package com.cnu.dao.core;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



import com.cnu.page.PageDiv;
/**
 * ��װ�������ݵĻ����������������Ƕ���
 * @param <T>
 */
public interface BaseDao<T extends Serializable>
{
	/**
	 * ͨ��sql���,����insert update delete create �Ȳ���
	 * insert into stu(name,age ) values(?,?);  'zhangsan' 18
	 * @param sql
	 * @param param
	 * @return
	 */
	public int updateBySql(String sql,Object...param)throws SQLException;
	/**
	 * ����ִ�и����޸Ĳ�����
	 * delete from admin where id=? and name=?
	 * @param sql ����PreparedStatement��SQL���
	 * @param params ��ά�������飬ÿ����һ��SQL���Ĳ�����
	 * @return ִ����Ӱ��ļ�¼��
	 */
	public int[] executeBatch(String sql,Object[]...params)throws SQLException; 
	/**
	 * ������sql��䣬��װ������䣬һ�𷢸����ݿ�
	 * set autocommit=0
	
	 * commit | ROLLBACK
	 * @param sql
	 * @return
	 */
	public int[] addBatch(String[]sql)throws SQLException;
	

	/**
	 * ����һ�����󣬽�t��������г�Ա����sql��䣬�������ݱ�
	 * @param t  �ͱ��Ӧ��bean
	 * @retuen ���������ݵ�idֵ
	 * @throws SQLException
	 */
	public int add(T t)throws SQLException;
	/**
	 * �޸�һ�����󣬸���t�����id,����t��id����Ϊ��
	 * @param t
	 * @throws SQLException
	 */
	public void update(T t)throws SQLException;
	/**
	 * ����t�е�idɾ����Ӧ����
	 * @param t
	 * @throws SQLException
	 */
	public void delete(Class<T> clazz,Integer id)throws SQLException;
	/**
	 * ͨ��id�õ�һ������
	 * @param clazz
	 * @return
	 * @throws SQLException
	 */
	public T getById(Class<T> clazz,T tem,Integer id)throws SQLException; 
	/**
	 * �õ�ָ�������������
	 * @param clazz
	 * @param sql
	 * @param params Null
	 * @return
	 * @throws SQLException
	 */
	public List<T> getAll(Class<T> clazz,String sql,Object...params)throws SQLException;
	/**
	 * �õ���ҳ����
	 * @param clazz  ���ұ��Ӧ��bean
	 * @param pageNo  ��ǰҳ��
	 * @param PageSize  //ÿҳ����
	 * @return
	 */
   // public PageDiv<T> getByPage(Class<T> clazz,int pageNo,int PageSize,Finder[] find,Order[] order)throws SQLException;
    /**
     * �õ�������
     * @param clazz
     * @param sql
     * @return
     */
    public int getTotalCount(String sql,Object...params)throws SQLException;
    
    
    /**
     * ͨ��sql����ҵ�һ������
     * @param clazz Ŀ������clazz
     * @param sql   select * from aa;
     * @param param Ԥ�������������Ϊnull
     * @return  ��Ӧ��ʵ�����
     */
    public T getByUniq(Class<T> clazz,String sql,Object...params)throws SQLException;
    /**
     * �ҳ�ǰ��������
     * @param clazz
     * @param sql
     * @param Ҫ��������
     * @param params
     * @return
     */
    public List<T> getByTop(Class<T> clazz,String sql,int top,Object...params)throws SQLException;
    /**
     * ͨ��sql����ҳ������
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
	 * ����ִ��sql��䣬��һ��sql�ظ��ö��
	 * demo:delete from t_name where id=? and xx=?
	 * @param sql   Ҫ����ִ�е�sql���
	 * @param params   ����Ĳ���Ϊ��ά����
	 * @return һά���ݣ�Ϊÿ��sql���Ӱ������ݱ�����
	 */
	public int[] batch(String sql,Object[][] params)throws SQLException;
	
	/**
     * ִ��sql���insert update delete
     * @param sql
     * @return  ����Ӱ�����ݱ������
     */
    public int executeUpdate(String sql)throws SQLException;
	
    /**
     * �������ݱ�inser update delete
     * demo:insert  t_name(aa,aa,ss) values(?,?,?) 
     * @param sql 
     * @param param  ����Ĳ���
     * @return ����Ӱ�����ݱ������
     */
    public int executeUpdate(String sql,Object...params)throws SQLException;
	/**
	 * ���ָ��sql�����ҵļ�¼����  sql����һ��Ҫ��selec count(*)��ʼ
	 * @param sql  sql���select count(id) from user;
	 * @return   ���ؼ�¼������
	 */
    public Integer executeQueryForCount(String sql)throws SQLException;
    /**
     * ��ѯһ����¼��������ض�����¼���򷵻ص�һ��
     * @param sql  sql���select * from user where id=12
     * @return  ����һ����¼�Ķ����װ
     */
    public T executeQueryForBean(String sql, Class<T> clazz)throws SQLException;
    /**
     * ��ѯһ����¼��������ض�����¼���򷵻ص�һ��
     * @param sql  sql���select * from user where id=?
     * @param params  �������?�Ĳ���
     * @return  ����һ����¼�Ķ����װ
     */
    public T executeQueryForBean(String sql, Class<T> clazz,Object...params)throws SQLException;

    
    /**
     * ��ѯ���ݱ������תΪһ��List
     * String sql=select * from user;
     * List<User> list=executeQuery(sql,User.class);
     * @param sql   Ҫִ�е�sql�ﹻ��
     * @param clazz  ����list��Ԫ�ص�����
     * @return   �������ݱ�����list��װ
     */
    public List<T> executeQuery(String sql,Class<T> clazz)throws SQLException; 
    /**
     * ��ѯ���ݱ������תΪһ��List
     * String sql=select * from user where id=?;
     * List<User> list=executeQuery(sql,User.class);
     * @param sql   Ҫִ�е�sql�ﹻ��
     * @param clazz  ����list��Ԫ�ص�����
     * @param param  Ϊ����Ĳ������������?
     * @return   �������ݱ�����list��װ
     */
    public List<T> executeQuery(String sql,Class<T> clazz,Object...params)throws SQLException;
    /**
     * ��ѯ����ÿ�������װΪObject[] ͶӰ��ѯ�����ڲ���һ����Ĳ�������
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

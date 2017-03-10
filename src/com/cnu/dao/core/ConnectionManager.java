package com.cnu.dao.core;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
/**
 * 管理Connection的工具类，用于得到Connection
 * @author Administrator
 */
public abstract class ConnectionManager
{
	 private static final String CONNECTIONIMP="ConnectionManager";
	 private static final Logger log=Logger.getLogger(ConnectionManager.class);
	 //用于放connection对象的本地线程副本
     private static ThreadLocal<Connection> cons=new ThreadLocal<Connection>();
     private static String impclass=null;
     public static ConnectionManager cm=null;
     //初始化当前类的实现类名
     static
     {
    	 ResourceBundle  res=ResourceBundle.getBundle("jdbc");//在classpath中查找 jdbc.properties配置文件
    	 impclass=res.getString(CONNECTIONIMP);   //当前实现类的全名配置 
     }
     private Connection con=null;
     /**
      * 获得一个Connection对象
      * @return
      */
     public Connection getConnection()
     {
    	con=cons.get();
    	try {
			if(null==con||con.isClosed())
			{
				cons.remove();
				con=this.getRealConnection();
				cons.set(con);
			}	
		} catch (SQLException e) {
			log.error("0001_ConnectionManager_打开Connection出错"+e);
		}
    	return con;
     }
    
     /**
     * 真正得到Connection的方法
     * @return
     */
     public abstract Connection getRealConnection();
    
     public void setAutoCommit(boolean auto)
     {
		 try
			{
			 if(null!=cm.getConnection()&&!cm.getConnection().isClosed())
				cm.getConnection().setAutoCommit(auto);
			} catch (SQLException e)
			{
				log.error("002_ConnectionManager_设置自动提交出错"+e);
				e.printStackTrace();
			}
     }
     
     public void commit()
     {  
    		 try
			{
				if(!cm.getConnection().getAutoCommit())cm.getConnection().commit();
			} catch (SQLException e)
			{
				log.error("003_ConnectionManager_提交出错"+e);
			}
    
     }
     public Savepoint setSavePoint(String name)
     {
    	 Savepoint  sp=null;
    	 try {
			 sp=cm.getConnection().setSavepoint(name);
		} catch (SQLException e) {
			log.error("0004_ConnectionManager_设置事务点出错");
			e.printStackTrace();
		}
    	 return sp;
     }
     public void rollback()
     {
    		 try
			{
    			if(!cm.getConnection().getAutoCommit())
				   cm.getConnection().rollback();
			} catch (SQLException e)
			{
				log.error("005_ConnectionManager__回滚出错"+e);
			}
    	 
     }
     public void rollback(Savepoint sp)
     {
    		 try
			{
    			if(!cm.getConnection().getAutoCommit())
				   cm.getConnection().rollback(sp);
			} catch (SQLException e)
			{
				log.error("006_ConnectionManager_回滚出错"+e);
			}
    	 
     }
     
     /**
     * 关闭链接
     */
     public void closeConnection()
     {
    		 try
			{
				 cm.getConnection().close();
				cons.remove();
				//if(con.isClosed())con=null;
			} catch (SQLException e)
			{
				log.error("007_ConnectionManager_关闭Connection出错,ConnectionManager_CloseConnection"+e);
			}
    	
     }
     /**
      * 用于得到当前ConnectionManager对象的方法
      * 一定是得到子类对象
      * @return
      */
     public static ConnectionManager getInstance()
     {
    	 if(null==cm)
    	 {
	    	try
			{
				cm=(ConnectionManager)Class.forName(impclass).newInstance();
			} catch (Exception e)
			{
				log.error("008_ConnectionManager__初始化ConnectionManager出错,ConnectionManager_getInstance_(jdbc.properties)配置文件出错"+e);
			}
    	 }
         return cm;

     }

}

package com.cnu.dao.core;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
/**
 * ����Connection�Ĺ����࣬���ڵõ�Connection
 * @author Administrator
 */
public abstract class ConnectionManager
{
	 private static final String CONNECTIONIMP="ConnectionManager";
	 private static final Logger log=Logger.getLogger(ConnectionManager.class);
	 //���ڷ�connection����ı����̸߳���
     private static ThreadLocal<Connection> cons=new ThreadLocal<Connection>();
     private static String impclass=null;
     public static ConnectionManager cm=null;
     //��ʼ����ǰ���ʵ������
     static
     {
    	 ResourceBundle  res=ResourceBundle.getBundle("jdbc");//��classpath�в��� jdbc.properties�����ļ�
    	 impclass=res.getString(CONNECTIONIMP);   //��ǰʵ�����ȫ������ 
     }
     private Connection con=null;
     /**
      * ���һ��Connection����
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
			log.error("0001_ConnectionManager_��Connection����"+e);
		}
    	return con;
     }
    
     /**
     * �����õ�Connection�ķ���
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
				log.error("002_ConnectionManager_�����Զ��ύ����"+e);
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
				log.error("003_ConnectionManager_�ύ����"+e);
			}
    
     }
     public Savepoint setSavePoint(String name)
     {
    	 Savepoint  sp=null;
    	 try {
			 sp=cm.getConnection().setSavepoint(name);
		} catch (SQLException e) {
			log.error("0004_ConnectionManager_������������");
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
				log.error("005_ConnectionManager__�ع�����"+e);
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
				log.error("006_ConnectionManager_�ع�����"+e);
			}
    	 
     }
     
     /**
     * �ر�����
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
				log.error("007_ConnectionManager_�ر�Connection����,ConnectionManager_CloseConnection"+e);
			}
    	
     }
     /**
      * ���ڵõ���ǰConnectionManager����ķ���
      * һ���ǵõ��������
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
				log.error("008_ConnectionManager__��ʼ��ConnectionManager����,ConnectionManager_getInstance_(jdbc.properties)�����ļ�����"+e);
			}
    	 }
         return cm;

     }

}

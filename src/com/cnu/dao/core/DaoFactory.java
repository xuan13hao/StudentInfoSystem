package com.cnu.dao.core;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
@SuppressWarnings("rawtypes")
public class DaoFactory
{
	 private static final Logger log=Logger.getLogger(DaoFactory.class);
	public static Map<Class,Object> daos=new HashMap<Class,Object>();
	static{
		ResourceBundle res= ResourceBundle.getBundle("dao");
		Enumeration<String> allkey=res.getKeys();
		while(allkey.hasMoreElements())
		{
			String key=allkey.nextElement();
			String value=res.getString(key);
		
			try
			{
				daos.put(Class.forName(key),Class.forName(value).newInstance());
			}catch (Exception e)
			{
				log.error("初始化Dao实现类出错"+e);
			}
		}
	}
	/**
	 *  
	 * @param clazz
	 * @return
	 */
    public static Object getDao(Class clazz)
    {
    	Object result=null;
    	result=daos.get(clazz);
    	if(null==result)
    	{
	    	ResourceBundle res= ResourceBundle.getBundle("dao");
	    	String imp=res.getString(clazz.getName());
	    	try
			{
				result=Class.forName(imp).newInstance();
				daos.put(clazz, result);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return result;
    }
}

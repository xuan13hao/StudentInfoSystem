package com.cnu.servlet.core;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnu.dao.core.DaoFactory;
import com.cnu.service.DatesDao;
import com.cnu.service.FileDao;
import com.cnu.service.FilesDao;
import com.cnu.service.FriendDao;
import com.cnu.service.UserDao;

public abstract class ServletBase extends HttpServlet
{
//protected Configuration cfg=null;
//protected boolean autoCommit=false;//是否要进行事务处理
private static final long serialVersionUID = 6784238681394572773L;
protected UserDao userDao=(UserDao)DaoFactory.getDao(UserDao.class);
protected FriendDao friendDao=(FriendDao)DaoFactory.getDao(FriendDao.class);
protected DatesDao datesDao=(DatesDao)DaoFactory.getDao(DatesDao.class);
protected FileDao fileDao=(FileDao)DaoFactory.getDao(FileDao.class);
protected FilesDao filesDao=(FilesDao)DaoFactory.getDao(FilesDao.class);
@Override
public void init() throws ServletException
{
    // Initialize the FreeMarker configuration;
    // - Create a configuration instance
   // cfg = new Configuration();
    // - Templates are stoted in the WEB-INF/templates directory of the Web app.
  //  cfg.setServletContextForTemplateLoading(
    //        getServletContext(), "WEB-INF/templates");
    // - Set update dealy to 0 for now, to ease debugging and testing.
    //   Higher value should be used in production environment.
    //cfg.setTemplateUpdateDelay(0);
    // - Set an error handler that prints errors so they are readable with
    //   a HTML browser.
   // cfg.setTemplateExceptionHandler( TemplateExceptionHandler.HTML_DEBUG_HANDLER);
    //// - Use beans wrapper (recommmended for most applications)
   // cfg.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
    // - Set the default charset of the template files
   // cfg.setDefaultEncoding("ISO-8859-1");
    // - Set the charset of the output. This is actually just a hint, that
    //   templates may require for URL encoding and for generating META element
    //   that uses http-equiv="Content-type".
   // cfg.setOutputEncoding("GBK");
    // - Set the default locale
   // cfg.setLocale(Locale.US);
}
/**
 * 用于方便地将模型数据合并到模版并输出到客户端
 * @param resp   response
 * @param root   模型数据可以是null
 * @param template   freemarker模版文件
 * @throws IOException
 */
/*public void mage(HttpServletResponse resp,Map<String,Object> root,String template) throws IOException
{
	resp.setContentType("text/html;charset=gbk");
	resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, "
            + "post-check=0, pre-check=0");
    resp.setHeader("Pragma", "no-cache");
    resp.setHeader("Expires", "Thu, 01 Dec 1994 00:00:00 GMT");

	PrintWriter out=resp.getWriter();

    Template t = cfg.getTemplate(template);
	try
	{
		t.process(root, out);
	} catch (TemplateException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}*/


/**
 * Servlet默认要访问的方法
 * @param req
 * @param resp
 * @throws ServletException
 * @throws IOException
 * @throws SQLException 
 */
public abstract void execute(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException, SQLException;

/**
 * 根据action参数，将用户请求，转向到Servlet中action对应的方法
 * 
 */
@Override
protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
{
	response.setContentType("text/html;charset=gbk");
	String action=request.getParameter("action");
	if(null==action||"".equals(action.trim()))
	{
		action="execute";
	}
	
	@SuppressWarnings("rawtypes")
	Class clazz[]=new Class[]{HttpServletRequest.class,HttpServletResponse.class};
	
		try
		{
			Method method=this.getClass().getMethod(action, clazz);
			//ConnectionManager.getInstance().setAutoCommit(false);
			method.invoke(this, new Object[]{request,response});
			//ConnectionManager.getInstance().commit();
		
		} catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		} catch (SecurityException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
		  // if(e.getTargetException().getClass()==SQLException.class)
		  // {
			   //ConnectionManager.getInstance().rollback();
		  // }
			e.printStackTrace();
		}
}


public String getString(HttpServletRequest req,String param)
{
      String result=null!=req.getParameter(param)?req.getParameter(param):"";
      return result;
}
public String[] getStringArray(HttpServletRequest req,String param)
{
     String []result=req.getParameterValues(param);
     return result;
}

public int getInt(HttpServletRequest req,String param)
{
	 int re=0;
      String str=this.getString(req, param);
      if(str.matches("\\d+"))
      {
    	 re=Integer.parseInt(str); 
      }
    return re;
}
public int[] getIntArray(HttpServletRequest req,String param)
{
	 int re[]=null;
	 //int temint=0;
     String strarr[]=this.getStringArray(req, param);
     if(null!=strarr&&strarr.length>0)
     {
    	 re=new int[strarr.length];
    	 for(int i=0;i<re.length;i++)
    	 {
    		 if(strarr[i].matches("\\d+"))
    	      {
    	    	 re[i]=Integer.parseInt(strarr[i]); 
    	      } 
    	 }
     }
      
    return re;
}
/**
 * 将用户请求参数帮定到一个bean对象
 * Article art=new Article();
 * this.getBean(req,art);
 * @param req
 * @param bean
 * @return
 */
@SuppressWarnings("rawtypes")
public void getBean(HttpServletRequest req,Object bean)
{
	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	if(null!=bean)
	{
		Class clazz=bean.getClass();
		Field [] allField=clazz.getDeclaredFields();
		for(Field field:allField)
		{
			field.setAccessible(true);
			String fieldName=field.getName();
			
			//我可通过req.getParams(fieldName);String
			String paramValue=this.getString(req, fieldName);
			if(null!=paramValue&&!"".equals(paramValue))
			{
				//取到了值 
				Class fieldType=field.getType();
				try {
					if(fieldType==java.lang.String.class)
					{
						//字符串
						field.set(bean, paramValue);
					}else  if(fieldType==java.lang.Integer.class)
					{
						field.set(bean, Integer.parseInt(paramValue));
						
					}else if(fieldType==int.class)
					{
						field.set(bean, Integer.parseInt(paramValue));
					}else if(fieldType==java.util.Date.class)
					{
						String pattern="\\d{4}[-]\\d{1,2}[-]\\d{1,2}[ ]\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}";
						if(paramValue.matches(pattern))
						{
							field.set(bean,sf.parse(paramValue));
						}
						String datepattern="\\d{4}[-]\\d{1,2}[-]\\d{1,2}";
						if(paramValue.matches(datepattern))
						{
							field.set(bean, new SimpleDateFormat("yyyy-MM-dd").parse(paramValue));
						}
						
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		
	}
}


/**
 * 获取网站配置信息
 * @param req
 * @param resp
 * @param path
 */
public String getWebInfo(String param)
{
	ServletContext sc=this.getServletContext();
	Properties p=(Properties)sc.getAttribute("webinfo");
	String re=null;
	if(null!=p)
		re=p.getProperty(param);
	return re;
	
}
public void forward(HttpServletRequest req,HttpServletResponse resp,String path)
{
   RequestDispatcher  rd=req.getRequestDispatcher("/WEB-INF/"+path);
	try
	{
		rd.forward(req,resp);
	} catch (ServletException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}

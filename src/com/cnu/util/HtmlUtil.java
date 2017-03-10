package com.cnu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class HtmlUtil 
{
	private static final Logger log=Logger.getLogger(HtmlUtil.class);
	/**
	 * ��һ��urlֱ��תΪString
	 * @param url
	 * @return
	 */
   public static String getStringByURL(String str)
   {
	   StringBuilder sb=new StringBuilder();
	     try {
			URL url=new URL(str);
			URLConnection ucon=url.openConnection();
			
			InputStream is=ucon.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			String tem=null;
			while(null!=(tem=br.readLine()))
			{
				sb.append(tem);
			}
			br.close();
			
		} catch (MalformedURLException e)
		{
			log.error("URL��ʽ����ȷ");
			e.printStackTrace();
		} catch (IOException e) {
			log.error("��URL���ִ���");
			e.printStackTrace();
		}   
	   return sb.toString();
   }
   /**
    * ��Ҫȫ·��
    * @param path
    * @param data
    */
   public static void saveFile(String path,String url)
   {
	   File f=new File(path);
	   PrintWriter out=null;
	try {
		out = new PrintWriter(f);
		  out.println(getStringByURL(url));
		   out.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
   }
   
   
   
}

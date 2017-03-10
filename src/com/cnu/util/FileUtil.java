package com.cnu.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileUtil {

	public static String readFile(String path)
	{
		StringBuilder sb=new StringBuilder();
		try 
		{
			BufferedReader br=new BufferedReader(new FileReader(path));
			String tem=null;
			while(null!=(tem=br.readLine()))
			{
				sb.append(tem+"\n");
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return sb.toString();
	}
}

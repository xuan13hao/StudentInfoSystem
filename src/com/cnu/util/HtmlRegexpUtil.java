package com.cnu.util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* <p>
* Title: HTML相关的正则表达式工具类
* </p>
* <p>
* Description: 包括过滤HTML标记，转换HTML标记，替换特定HTML标记
* </p>
*/

public class HtmlRegexpUtil {
public  final static Pattern   PATTERN   =   Pattern.compile("<img\\s+(?:[^>]*)src\\s*=\\s*([^>]+)",   Pattern.CASE_INSENSITIVE   |   Pattern.MULTILINE);  
private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

@SuppressWarnings("unused")
private final static String regxpForImgTag = "<\\s*img\\s+([^>]*)\\s*>"; // 找出IMG标签

@SuppressWarnings("unused")
private final static String regxpForImaTagSrcAttrib = "src=\"([^\"]+)\""; // 找出IMG标签的SRC属性

/**
*
*/
public HtmlRegexpUtil() {
   // TODO Auto-generated constructor stub
}

/**
*
* 基本功能：替换标记以正常显示
* <p>
*
* @param input
* @return String
*/
public String replaceTag(String input) {
   if (!hasSpecialChars(input)) {
    return input;
   }
   StringBuffer filtered = new StringBuffer(input.length());
   char c;
   for (int i = 0; i <= input.length() - 1; i++) {
    c = input.charAt(i);
    switch (c) {
    case '<':
     filtered.append("&lt;");
     break;
    case '>':
     filtered.append("&gt;");
     break;
    case '"':
     filtered.append("&quot;");
     break;
    case '&':
     filtered.append("&amp;");
     break;
    default:
     filtered.append(c);
    }

   }
   return (filtered.toString());
}

/**
*
* 基本功能：判断标记是否存在
* <p>
*
* @param input
* @return boolean
*/
public boolean hasSpecialChars(String input) {
   boolean flag = false;
   if ((input != null) && (input.length() > 0)) {
    char c;
    for (int i = 0; i <= input.length() - 1; i++) {
     c = input.charAt(i);
     switch (c) {
     case '>':
      flag = true;
      break;
     case '<':
      flag = true;
      break;
     case '"':
      flag = true;
      break;
     case '&':
      flag = true;
      break;
     }
    }
   }
   return flag;
}

/**
*
* 基本功能：过滤所有以"<"开头以">"结尾的标签
* <p>
*
* @param str
* @return String
*/
public static String filterHtml(String str) {
   Pattern pattern = Pattern.compile(regxpForHtml);
   Matcher matcher = pattern.matcher(str);
   StringBuffer sb = new StringBuffer();
   boolean result1 = matcher.find();
   while (result1) {
    matcher.appendReplacement(sb, "");
    result1 = matcher.find();
   }
   matcher.appendTail(sb);
   return sb.toString();
}

public static String getDesc(String str,int wordsCount)
{
	StringBuffer res=new StringBuffer();
	String str1=filterHtml(str);
	int len=str1.length();
	if(len>wordsCount)
	{
		res.append(str1.substring(0, wordsCount));
		res.append("...");
	}else
	{
		res.append(str1);
	}
	
	return res.toString();
}

/**
*
* 基本功能：过滤指定标签
* <p>
*
* @param str
* @param tag
*            指定标签
* @return String
*/
public static String fiterHtmlTag(String str, String tag) {
   String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
   Pattern pattern = Pattern.compile(regxp);
   Matcher matcher = pattern.matcher(str);
   StringBuffer sb = new StringBuffer();
   boolean result1 = matcher.find();
   while (result1) {
    matcher.appendReplacement(sb, "");
    result1 = matcher.find();
   }
   matcher.appendTail(sb);
   return sb.toString();
}

/**
*
* 基本功能：替换指定的标签
* <p>
*
* @param str
* @param beforeTag
*            要替换的标签
* @param tagAttrib
*            要替换的标签属性值
* @param startTag
*            新标签开始标记
* @param endTag
*            新标签结束标记
* @return String
* @如：替换img标签的src属性值为[img]属性值[/img]
*/
public static String replaceHtmlTag(String str, String beforeTag,
    String tagAttrib, String startTag, String endTag) {
   String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";
   String regxpForTagAttrib = tagAttrib + "=\"([^\"]+)\"";
   Pattern patternForTag = Pattern.compile(regxpForTag);
   Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
   Matcher matcherForTag = patternForTag.matcher(str);
   StringBuffer sb = new StringBuffer();
   boolean result = matcherForTag.find();
   while (result) {
    StringBuffer sbreplace = new StringBuffer();
    Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag
      .group(1));
    if (matcherForAttrib.find()) {
     matcherForAttrib.appendReplacement(sbreplace, startTag
       + matcherForAttrib.group(1) + endTag);
    }
    matcherForTag.appendReplacement(sb, sbreplace.toString());
    result = matcherForTag.find();
   }
   matcherForTag.appendTail(sb);
   return sb.toString();
}
public  static List<String> getImgSrc(String html)  
{  
    Matcher   matcher=PATTERN.matcher(html);  
    List<String> list= new ArrayList<String>();  
    while(matcher.find())  
    {  
        String group=matcher.group(1);  
        if(group==null)  
        {  
            continue;  
        }  
        //   这里可能还需要更复杂的判断,用以处理src="...."内的一些转义符  
        if(group.startsWith("'"))  
        {  
            list.add(group.substring(1,group.indexOf("'",   1)));  
        }else if(group.startsWith("\""))
        {  
            list.add(group.substring(1,group.indexOf("\"",   1)));  
        }else
        {  
            list.add(group.split("\\s")[0]);  
        }  
    }  
    return   list;  
}
/**
 * 找出html代码中夹的图片
 * @param org
 * @return
 */
public static String getAllPics(String org)
{
	
	 StringBuffer contentspics=new StringBuffer();
     List<String> pics=HtmlRegexpUtil.getImgSrc(org);
		for(String tem:pics)
		{
			String finalname=null;
			int index=0;
			index=tem.lastIndexOf("/");
			if(-1!=index)
				finalname=tem.substring(index+1);
			contentspics.append(finalname+"||");
		}
	return contentspics.toString();
}
/**
 * 将文本框格式转为html
 * @param str
 * @return
 */
public static String toHtml(String str)
{
    if(str==null)
        return null;
    StringBuffer sb = new StringBuffer();
    int len = str.length();
    for (int i = 0; i < len; i++)
    {
        char c = str.charAt(i);
        switch(c)
        {                  
        case ' ':
            sb.append("&nbsp;");
            break;
        case '\n':
            sb.append("<br>");
           break;
        case '\r':
           break;
        case '\'':
            sb.append("&#39;");
            break;
        case '<':
            sb.append("&lt;");
            break;
        case '>':
            sb.append("&gt;");
            break;
        case '&':
            sb.append("&amp;");
            break;
        case '"':
            sb.append("&#34;");
            break;
        case '\\':
            sb.append("&#92;");
            break;
        default:
            sb.append(c);
        }
    }
    return sb.toString();
}   
/**
 * 在html代码中找出图片文件名，并用&&号进行链接
 * @param html
 * @return
 */
public static String parseImagefromHtml(String html)
{
	List<String> pics=HtmlRegexpUtil.getImgSrc(html);
	StringBuilder sb=new StringBuilder();
	if(null!=pics&&pics.size()>0)
	{
		for(int i=0;i<pics.size();i++)
		{
			String allPath=pics.get(i);
			if(allPath.startsWith("http://")||allPath.startsWith("https://")||allPath.startsWith("ftp://"))
			{
				continue;
			}
			int index=allPath.lastIndexOf("/");
			if(index!=-1)
			{
				String fileName=allPath.substring(index+1);
				    if(i==pics.size()-1)
				    sb.append(fileName);
					else
					sb.append(fileName+"&&");
			}

		}
	}

	return sb.toString();
}


public static void main(String[] args) 
{  
        String html="<html>\r\n"+  
                "<head><title>test</title><head>\r\n"   +  
                "<body>"   +  
                "<P><IMG   height=\"100\"       src='abc.png'   weight=\"30\">abcdefg"   +  
                "<img   src='http://abc.xyz.com/123/456.jpg'   /><br>"   +  
                "<IMG   height=\"100\"       \r\n"   +  
                "       src=\"abc.jpg\"   \r\n"   +  
                "   weight=\"30\">abcdefg         \r\n"   +  
                "   <img   src=ttt.jpg>"   +  
                "   <img   src=\"123.jpg\"   />"   +  
//                 "<img   alt=\"src='abc'\">"   +   //这种我也无能为力  
                "</body></html>";  
      
        System.out.println(getImgSrc(html));  
        
        
        
} 

}
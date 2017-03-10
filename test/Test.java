import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.redcms.annotation.Column;
import com.redcms.beans.ModelItem;


	public class Test {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		
	ModelItem mi=new ModelItem();
	
	Class<ModelItem> clazz=(Class<ModelItem>) mi.getClass();
	//Class field method constr...
/*    Annotation  an=clazz.getAnnotation(Table.class);
    if(an instanceof Table)
    {
    	Table t=(Table)an;
    	System.out.println(t.value());
    }
	   
	*/
	
	
	Field f=clazz.getDeclaredField("modelId");
	Annotation  an=f.getAnnotation(Column.class);
	if(null!=an&&an instanceof Column)
	{
		Column col=(Column)an;
		System.out.println(col.value());
	}
		
	
	}
}

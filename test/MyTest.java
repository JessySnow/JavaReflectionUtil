package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;

import bean.Book;
import util.refUtil;

public class MyTest {
    
    @Test
    public void testOnGetClassByName(){
        Object object = refUtil.getClassByName("bean.Book");
        System.out.println(object);
        assertNotNull(object);
    }

    @Test
    public void testOnGetConstutors(){
        assertEquals(refUtil.getAllConstructors(Book.class).length, 3);
    }

    @Test
    public void testOnGetSpiConstructor(){
        assertEquals(String.valueOf(refUtil.getConstructorByPreciseParameter(Book.class, String.class)), "public bean.Book(java.lang.String)");
        assertEquals(String.valueOf(refUtil.getConstructorByPreciseParameter(Book.class, int.class, String.class)), "public bean.Book(int,java.lang.String)");
        assertEquals(String.valueOf(refUtil.getConstructorByPreciseParameter(Book.class)), "public bean.Book()");
    }

    @Test
    public void testOnGetParameters(){
        assertEquals(
            refUtil.getConstructorParameters(refUtil.getConstructorByPreciseParameter(Book.class, int.class, String.class)).length, 2);
    }

    @Test
    public void testOnGenerateANewObject(){
        Book book =(Book) refUtil.getAInstanceWithoutParamter(
            refUtil.getConstructorByPreciseParameter(Book.class)
        );
        assertEquals(book.toString(), "Book [ id=0, title=null]");
    }

    /**
     * 没有 public 域
     */
    @Test
    public void testOnGettingField(){
        Field[] fields = refUtil.getAllPublicFields(Book.class);
        assertEquals(2, fields.length);
    }

    @Test
    public void testOnGetFieldsName(){
        String[] names = refUtil.getAllPublicFieldsName(Book.class);
        for(String name : names) System.out.println(name);
    } 

    @Test
    public void testOnGetAllMethods(){
        Method[] methods = refUtil.getAllPublicMethods(Book.class);
        for(Method method : methods)
            System.out.println(method);
    }

    @Test
    public void testOnGetSpiMethod(){
        Method method = refUtil.getMethodWithSpiParamter(bean.Book.class, "getId", null);
        System.out.println(method);
    }

    @Test
    public void testOnTypesOfMethod(){
       Method method = refUtil.getMethodWithSpiParamter(Book.class, "setId", new Class[]{int.class});
       Class[] parameters = refUtil.getParametersByMethos(method);
       Class retType = refUtil.getRetTypesByMethods(method);
       assertEquals(retType, void.class);
       assertEquals(parameters[0], int.class);
    }

    @Test
    public void testOnGetAllField(){
        Field[] fields = refUtil.getAllFields(Book.class);
        for(Field field : fields)   System.out.println(field);
    }

    @Test
    public void testOnGetSpiFieldIP(){
        Field field = refUtil.getSpiFieldIP(Book.class, "id");
        assertEquals(String.valueOf(field), "private int bean.Book.id");
    }

    @Test
    public void testOnGetAllMethodsIP(){
        Method[] methods = refUtil.getAllMethods(Book.class);
        for(Method method : methods) System.out.println(method);
    }

    @Test
    public void testOnGetSpiMethodIP(){
        Method method = refUtil.getSpiMethod(Book.class, "testOnGetMethod", new Class[]{String.class, String.class, List.class});
        System.out.println(method);
    }
}

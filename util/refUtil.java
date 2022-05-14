package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 */
public class refUtil {
    
    /**
     * 利用类名获取类的 Class 对象
     * @param classname
     * @return
     */
    public static Class getClassByName(String classname){
        try {
            return Class.forName(classname);
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
            return null;
        }
    }

    /**
     * 获取类对象的所有构造函数
     * @param class1
     * @return
     */
    public static Constructor[] getAllConstructors(Class class1){
        if(class1 != null)
            return class1.getConstructors();
        return null;
    }

    /**
     * 获取特定的构造器
     * @param target
     * @param classes
     * @return
     */
    public static Constructor getConstructorByPreciseParameter(Class target,Class ... classes){
        if(classes.length > 0)
            try {
                return target.getConstructor(classes);
            } catch (NoSuchMethodException e) {
                System.out.println("No Such class");
            } catch (SecurityException e) {
                System.out.println("No Such Constructor Or Constructor is invisiable");
            }
        else
            try {
                return target.getConstructor();
            } catch (NoSuchMethodException e) {
                System.out.println("No Such class");
            } catch (SecurityException e) {
                System.out.println("No Such Constructor Or Constructor is invisiable");
            }

        return null;
    }

    /**
     * 获取构造函数的参数类型
     * @param constructor 
     * @return
     */
    public static Class[] getConstructorParameters(Constructor constructor) {
        return constructor != null ? constructor.getParameterTypes() : null;
    }


    // TODO:理解泛型类的创建过程
    /**
     * 使用无参构造函数构造一个对象
     * @param <T>
     * @return
     */
    public static <T> T getAInstanceWithoutParamter(Constructor<T> constructor){
        try {
            return constructor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    // TODO:理解泛型类的创建过程
    // TODO: 使用 setter 方法进行对象构造
    /**
     * 使用无参构造函数构造一个对象
     * 并使用对应的参数进行组装
     * @param <T>
     * @return
     */
    public static <T> T getAInstanceWithParamter(Constructor<T> constructor, Object ... args){
        try {
            return constructor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    /****************************** Class And Constructor *********************************/

    /**
     * 获取该类型对象的所有 被public修饰的域
     * @param class1
     * @return 
     * @see testOnGettingField
     */
    public static Field[] getAllPublicFields(Class class1){
        if(class1 == null) return null;
        return class1.getFields();
    }

    /**
     *  获取所有 public 域的名字
     * @param class1
     * @return
     */
    public static String[] getAllPublicFieldsName(Class class1){
        if(class1 != null){
            Field[] fields = class1.getFields();
            String[] names = new String[fields.length];
            for(int i = 0; i < fields.length; ++ i)
                names[i] = fields[i].getName();
            return names;
        }
        return null;
    }    

    /**
     * 获取所有的域,包括私有域
     * @param class1
     * @return
     */
    public static Field[] getAllFields(Class class1){
        if(class1 == null) return null;
        return class1.getDeclaredFields();
    }

    /**
     * 根据名字，获取特定的域，包括私有域
     * @param class1
     * @return
     */
    public static Field getSpiFieldIP(Class class1, String name){
        if(class1 == null) return null;
        try {
            return class1.getDeclaredField(name);
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO:调用 Field 的 get、set 方法进行类型组装
    /*********************************** Field *************************************/

    public static Method[] getAllPublicMethods(Class class1){
        if(class1 != null){
            Method[] methods = class1.getMethods();
            return methods;
        }
        return null;
    }


    /**
     * 返回特定方法签名的方法
     * @param class1
     * @return
     */
    public static Method getMethodWithSpiParamter(Class class1, String name, Class[] parameters){
        try {
            return class1.getMethod(name, parameters);
        } catch (NoSuchMethodException | SecurityException e) {
            System.out.println("No such Method Or the method is invisable");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回传入方法对象的所有参数类型
     * @param method
     * @return
     */
    public static Class[] getParametersByMethos(Method method){
        return method.getParameterTypes();
    }    

    /**
     * 返回传入方法对象的返回类型
     * @param method
     * @return
     */
    public static Class getRetTypesByMethods(Method method){
        return method.getReturnType();
    }

    /**
     * 获取所有的方法，包括私有的
     * @param class1
     * @return
     */
    public static Method[] getAllMethods(Class class1){
        if(class1 != null){
            return class1.getDeclaredMethods();
        }
        return null;
    }

    /**
     * 通过方法名和参数返回一个 Method 对象
     * @param class1
     * @return
     */
    public static Method getSpiMethod(Class class1, String name, Class[] args){
        if(class1 != null){
            try {
                return class1.getDeclaredMethod(name, args);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    
    // TODO:泛型方法调用
    /*********************************** Methods *************************************/
}

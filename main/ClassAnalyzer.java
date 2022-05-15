import java.lang.reflect.Method;

import util.refUtil;

/**
 * 使用反射工具输出类的信息
 * 
 * 输出顺序：
 *  >1. 类的全限定类名、类的简写类名
 *  >2. 类的所有域
 *  >3. 类的所有方法，格式：
 *      [modifier] returnType methodName(paramType param1, paramType param2)
 */
public class ClassAnalyzer {

    private static final String warn1 = "在命令行参数中指定类的全限定类名";
    private static final String warn2 = "无法找到类名指定的类";
    private static final String line = "\n*************************** %s *****************************\n";

    private static final String nameFormat = "类的全限定类名: %s\n, 类的简写类名: %s\n";
    private static final String fieldFormat = "%s%s";

    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println(warn1);
            System.exit(1);
        }

        String className = args[0];
        Class class1 = null;

        try {
            class1 = Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println(warn2 + " " + warn1);
            System.exit(1);
        }

        System.out.printf(line, "Class Name");

        /**
         * 输出类的全限定类名
         * 输出类的简写类名
        */
        String simpleNames = refUtil.getSimpleClassName(class1);
        System.out.printf(nameFormat, className, simpleNames);

        System.out.printf(line, "Fields");

        /**
         * 输出在类中声明的所有域
        */
        String[] fieldName = refUtil.getSimpleFieldName(class1);
        String[] fieldModifiers = refUtil.getAllModifiers(class1);
        for(int i = 0; i < fieldModifiers.length; ++ i)
            System.out.printf(fieldFormat, fieldModifiers[i], fieldName[i]);

        System.out.printf(line, "Methods");

        /**
         * 输出在类中声明的所有的方法
        */
        Method[] methods = refUtil.getAllMethods(class1);
        for(Method method : methods)    System.out.println(method);

    }
}

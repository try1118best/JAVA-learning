package yichang;

import java.lang.reflect.Method;
import java.security.PublicKey;

public class Test {
    public static void main1(String[] args) {
        try {
            int[] array = {1, 2, 3};
            System.out.println(array[3]);//ArrayIndexOutOfBoundsException：数组越界异常
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("finally...");
        }
    }

    public static void main2(String[] args) {//ArithmeticException：表示数学运算异常
        System.out.println(6 / 0);
    }

    public static void main3(String[] args) throws Exception {
        Class clazz = Class.forName("Found");//ClassNotFoundException：表示类未定义异常
        System.out.println(clazz);
    }

    public static void main4(String[] args) throws Exception {
        Class clazz = Class.forName("yichang.Factory"); // 注意这里的包名
        System.out.println(clazz);
    }

    public static void main5(String[] args) throws Exception {
        Class clazz = Class.forName("yichang.Factory");
        Method test = clazz.getMethod("test", Integer.class);
        test.invoke(new Factory(), 1.1);//IllegalArgumentException：表示参数格式错误异常
    }

    public static void main6(String[] args) {
        Integer integer = null;//new Integer(1);
        System.out.println(integer.equals(1));//NullPointerException：空指针异常
    }

    public static void main7(String[] args) throws Exception {
        Class clazz = Class.forName("yichang.Factory");
        Method test = clazz.getMethod("test1", Integer.class);
        test.invoke(new Factory(), 1);//NoSuchMethodException: 方法未定义异常
    }

    public static void main8(String[] args) {
        Integer integer = Integer.parseInt("a");
        System.out.println(integer);    //NumberFormatException: 数值类型转换异常
    }

    /*--------------------------throw 和 throws----------------------*/
    //1.throw 是主动抛出一个异常对象
    public static void main9(String[] args) throws Exception {
        Exception exception = new Exception("测试错误");
        throw exception;
    }

    //2.throws 是用来声明方法可能会抛出异常
    public static void main10(String[] args) {
        try {
            test1(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test1(int index) throws Exception {
        int[] array = {1, 2, 3};
        System.out.println(array[index]);
    }

    /*--------------------------自定义异常类----------------------*/
    public static void main(String[] args) {
        Test test = new Test();
        try {
            int num =test.add("hello");
        } catch (MyNumberException e) {
            e.printStackTrace();
        }
    }

    public int add(Object object) throws MyNumberException{
        if(!(object instanceof Integer)){
            String error = "传入的参数不是整数类型";
            throw new MyNumberException(error);
        } else {
            int num =(int) object;
            return num++;
        }
    }
}

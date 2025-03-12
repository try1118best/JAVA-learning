import sun.applet.Main;
import java.io.File;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main1(String[] args) {
        String str = "Hello";
        Class<String> stringClass = String.class;
        Field[] fields = stringClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("value")) {
                System.out.println(field);
                try {
                    field.setAccessible(true);
                    //Object o = field.get(str);
                    char[] array = (char[]) field.get(str);
                    //System.out.println(array);
                    for (char c : array) {
                        System.out.println(c);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println(field.getName());
        }
        System.out.println(str);
        str = "HelloWorld";
        System.out.println(str);
    }

    //StringBuffer
    public static void main2(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(stringBuffer);
        System.out.println(stringBuffer.length());
        stringBuffer = new StringBuffer("Hello World");
        System.out.println(stringBuffer);
        System.out.println(stringBuffer.charAt(2));
        stringBuffer = stringBuffer.append("Java");
        System.out.println(stringBuffer);
        stringBuffer = stringBuffer.delete(3, 6);
        System.out.println(stringBuffer);
        stringBuffer = stringBuffer.deleteCharAt(3);
        System.out.println(stringBuffer);
        stringBuffer = stringBuffer.replace(2, 3, "StringBuffer");
        System.out.println(stringBuffer);
        String str = stringBuffer.substring(2);
        System.out.println(str);
        str = stringBuffer.substring(2, 8);
        System.out.println(str);
        stringBuffer = stringBuffer.insert(6, "six");
        System.out.println(stringBuffer);
        System.out.println(stringBuffer.indexOf("e"));
        System.out.println(stringBuffer.indexOf("e", 6));
        stringBuffer = stringBuffer.reverse();
        System.out.println(stringBuffer);
        String string = stringBuffer.toString();
        System.out.println(string);
    }

    //时间类

    //Date
    public static void main3(String[] args) {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd " +
                "HH:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }

    //Calendar
    public static void main4(String[] args) {
        //计算2024-09-23所在的周是2024年的第几周
        //将数据存入
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.DAY_OF_MONTH, 23);
        //将数据取出
        int i = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("2024年9月23号所在的周是2024年的第" + i + "周");
    }

    public static void main5(String[] args) {
        //2024-09-23之后200天是哪一年的几月几号
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.DAY_OF_MONTH, 23);
        int i = calendar.get(Calendar.DAY_OF_YEAR);
        System.out.println("2024年9月23号是2024年的第" + i + "天");
        int n = i + 200;
        calendar.set(Calendar.DAY_OF_YEAR, n);
        Date time = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(time);
        System.out.println("2024-09-23之后200天是" + format);
    }

    public static void main6(String[] args) {
        //2024-9-23往前200天是几月几号
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DAY_OF_MONTH, 23);
        int i = calendar.get(Calendar.DAY_OF_YEAR);
        System.out.println(" 2024年9月23号是2024年的第" + i + "天");
        int n = i - 1000;
        calendar.set(Calendar.DAY_OF_YEAR, n);
        Date time = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(time);
        System.out.println("2024-09-23往前200天是" + format);
    }
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\java\\test.txt");
        if (file.exists()) {
            String name = file.getName();
            System.out.println(name);
            long length = file.length();
            System.out.println(length);
            String path = file.getPath();
            System.out.println(path);
            String parent = file.getParent();
            System.out.println(parent);
            File parentFile = file.getParentFile();
            System.out.println(parentFile.isDirectory());
            System.out.println(parentFile.isFile());
            System.out.println(file.isDirectory());
            System.out.println(file.isFile());
            File file2 = new File("D:\\java\\test2.txt");
            System.out.println(file2);
            System.out.println(file2.createNewFile());
            System.out.println(file2.delete());
        }
    }
}

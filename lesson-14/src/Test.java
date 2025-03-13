import sun.applet.Main;

import java.io.*;

public class Test {
    public static void main1(String[] args) throws Exception {
        File file = new File("D:\\java\\test.txt");
        System.out.println(file);
        System.out.println(file.exists());
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
            System.out.println(parentFile.isDirectory());//判断是不是文件夹
            System.out.println(parentFile.isFile());
            System.out.println(file.isFile());
            System.out.println(file.isDirectory());
            File file2 = new File("D:\\java\\test2.txt");
            System.out.println(file2);
            System.out.println(file2.createNewFile());
            System.out.println(file2.delete());
        }
    }

    public static void main2(String[] args) throws Exception {
        File file = new File("D:\\java\\test.txt");
        InputStream inputStream = new FileInputStream(file);
        int available = inputStream.available();
        System.out.println(available);
        int read = inputStream.read();
        System.out.println(read);
        int read1 = inputStream.read();
        System.out.println(read1);
        int read2 = inputStream.read();
        System.out.println(read2);
        int read3 = inputStream.read();
        System.out.println(read3);
        System.out.println("****循环后****");
    }

    public static void main3(String[] args) throws Exception {
        File file = new File("D:\\java\\test.txt");
        InputStream inputStream = new FileInputStream(file);
        int available = inputStream.available();
        System.out.println("文件当前未读取的数据还有：" + available);
        for (int i = 0; i < available; i++) {
            int read = inputStream.read();
            System.out.println(read);
            int available1 = inputStream.available();
            System.out.println("文件当前未读取的数据还有：" + available1);
        }
    }

    public static void main4(String[] args) throws Exception {
        File file = new File("D:\\java\\test.txt");
        InputStream inputStream = new FileInputStream(file);
        int temp = 0;
        while ((temp = inputStream.read()) != -1) {
            //int read = inputStream.read();
            System.out.println(temp);
        }
    }

    public static void main5(String[] args) throws Exception {
        File file = new File("D:\\java\\test.txt");
        InputStream inputStream = new FileInputStream(file);
        int available = inputStream.available();
        System.out.println("文件当前未读取的数据：" + available);
        int temp = 0;
        while ((inputStream.available()) > 0) {
            System.out.println(inputStream.read());
        }
    }

    public static void main6(String[] args) throws Exception {
        File file = new File("D:\\java\\test.txt");
        InputStream inputStream = new FileInputStream(file);

        byte[] bytes = new byte[11];
        int length = inputStream.read(bytes, 2, 6);
        System.out.println(length);
        //int read = inputStream.read(bytes,2,6);
        //System.out.println(read);
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        inputStream.close();
    }

    //OutputStream
    public static void main7(String[] args) throws Exception {
        OutputStream outputStream = new FileOutputStream("D:\\java\\test2.txt");
        byte[] bytes = {105, 111, 99};
        outputStream.write(bytes, 1, 2);
        outputStream.flush();
        outputStream.close();
    }

    public static void main8(String[] args) throws Exception {
        //先读数据
        InputStream inputStream = new FileInputStream("D:\\java\\test.txt");
        OutputStream outputStream = new FileOutputStream("D:\\java\\test3.txt");
        int temp = 0;
        while ((temp = inputStream.read()) != 1) {
            outputStream.write(temp);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    //Reader
    public static void main9(String[] args) throws Exception {
        Reader reader = new FileReader("D:\\java\\test.txt");
        int temp = 0;
        while ((temp = reader.read()) != -1) {
            System.out.println(temp);
        }
        reader.close();
        System.out.println("*************");
        InputStream inputStream = new FileInputStream("D:\\java\\test.txt");
        int temp1 = 0;
        while ((temp1 = inputStream.read()) != -1) {
            System.out.println(temp1);
        }
    }

    public static void main10(String[] args) throws Exception {
        Reader reader = new FileReader("D:\\java\\test.txt");
        char[] chars = new char[8];
        int length = reader.read(chars);
        System.out.println(length);
        for (char aChar : chars) {
            System.out.println(aChar);
        }
        reader.close();
    }

    public static void main11(String[] args) throws Exception {
        Writer writer = new FileWriter("D:\\java\\test3.txt");
        //writer.write(20320);
        //writer.write(22909);
        //char[] chars = {'你','好','世','界',};
        //writer.write(chars,2,2);
        String str = "Hello World,你好世界";
        char[] chars = str.toCharArray();
        writer.write(chars, 2, 3);
        writer.flush();
        writer.close();
    }

    //file copy
    public static void main(String[] args) throws Exception {
        Reader reader = new FileReader("D:\\java\\test3.txt");
        Writer writer = new FileWriter("D:\\java\\test4.txt");
        int temp = 0;
        int count = 0;
        while (((temp = reader.read())) != -1) {
            writer.write(temp);
            count++;
        }
        System.out.println(count);
        writer.flush();
        writer.close();
        reader.close();
    }
}

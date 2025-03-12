public class Test {
    public static void main1(String[] args) {
        //直接赋值
        String str1 = "Hello";
        //通过构造器
        String str2 = new String("World");
        System.out.println(str1);
        System.out.println(str2);
    }

    //以上两种实例化有何不同，哪种更优？
    public static void main2(String[] args) {
        String str1 = "Hello";
        String str2 = "Hello";
        System.out.println(str1 == str2);
        String str3 = new String("World");
        String str4 = new String("World");
        System.out.println(str3 == str4);
        System.out.println("************");
        System.out.println(str1.equals(str2));
        System.out.println(str3.equals(str4));
    }

    public static void main(String[] args) {
        char[] array = {'J', 'a', 'v', 'a', ',', 'H', 'e', 'l', 'l', 'o', ','
                , 'W', 'o', 'r', 'l', 'd'};
        String str = new String(array);
        System.out.println(str);
        System.out.println(str.length());
        System.out.println(str.isEmpty());
        System.out.println(str.charAt(2));
        System.out.println(str.indexOf('H'));
        String str2 = "Java,Hello,World";
        System.out.println(str.equals(str2));
        String str3="Hello";
        String str4="HELLO";
        System.out.println(str3.equals(4));
        System.out.println(str3.equalsIgnoreCase(str4));
        System.out.println("-----------");
        System.out.println(str.startsWith("Java"));
        System.out.println(str.endsWith("World"));
        System.out.println(str.substring(2));
        System.out.println(str.substring(2, 6));
        System.out.println(str.replaceAll("World", "Java"));
        System.out.println(str);
        System.out.println("************");
        String[] split = str.split(",");
        for (String s : split) {
            System.out.println(s);
        }
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            System.out.println(aChar);
        }
        System.out.println(str.toLowerCase());
        System.out.println(str.toUpperCase());
    }
}

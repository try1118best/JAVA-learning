import java.util.Random;

public class Test {
    //枚举
    public static void main1(String[] args) {
        System.out.println(Week.MONDAY);
        System.out.println(Week2.MONDAY);
    }

    //Math
    public static void main2(String[] args) {
        System.out.println(Math.E);
        System.out.println(Math.PI);
        System.out.println(Math.sqrt(9));       //9开平方
        System.out.println(Math.cbrt(8));       //8开立方
        System.out.println(Math.pow(2, 3));     //2的3次方
        System.out.println(Math.max(6.3, 3.5));
        System.out.println(Math.min(6.3, 3.5));
        System.out.println(Math.abs(-10.3));    //绝对值
        System.out.println(Math.ceil(10.0001)); //求当前数最大整数
        System.out.println(Math.floor(10.999)); //求当前数最小整数
        System.out.println(Math.random());      //0~9 的 随机数
        System.out.println(Math.rint(5.6));     //四舍五入
    }

    //Random
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            boolean b = random.nextBoolean();
            System.out.println("第" + i + "个随机数：" + b);
        }
        System.out.println();

        for (int i = 0; i < 3; i++) {
            double v =random.nextDouble();
            System.out.println("第" + i + "个随机数：" + v);
        }
        System.out.println();

        for (int i = 0; i < 3; i++) {
            float v =random.nextFloat();
            System.out.println("第" + i + "个随机数：" + v);
        }
        System.out.println();

        for (int i = 0; i < 3; i++) {
            int i1 =random.nextInt();
            System.out.println("第" + i + "个随机数：" + i1);
        }
        System.out.println();

        for (int i = 0; i < 3; i++) {
            long l =random.nextLong();
            System.out.println("第" + i + "个随机数：" + l);
        }
    }
}
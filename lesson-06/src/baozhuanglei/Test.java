package baozhuanglei;

public class Test {
    public static void main(String[] args) {
        //装箱
        //int num = null;
        Integer num = null;
        byte b = 1;
        Byte byt = new Byte(b);
        short s = 2;
        Short sho = new Short(s);
        int i = 3;
        Integer integer = new Integer(i);
        long l = 4;
        Long lon = new Long(l);
        float f = 5.5f;
        Float flo = new Float(f);
        double d = 6.6;
        Double dou = new Double(d);
        char cha = 'j';
        Character character = new Character(cha);
        boolean bol = true;
        Boolean bool = new Boolean(bol);
        System.out.println(byt.toString());
        System.out.println(sho);
        System.out.println(integer);
        System.out.println(num);
        System.out.println(lon);
        System.out.println(flo);
        System.out.println(dou);
        System.out.println(character);
        System.out.println(bool);
        System.out.println("拆箱后：");
        byte b1 = byt.byteValue();
        short s1 = sho.shortValue();
        int i1 = integer.intValue();
        long l1 = lon.longValue();
        float f1 = flo.floatValue();
        double d1 = dou.doubleValue();
        char c1 = character.charValue();
        boolean b2 = bool.booleanValue();
        System.out.println(b1);
        System.out.println(s1);
        System.out.println(i1);
        System.out.println(l1);
        System.out.println(f1);
        System.out.println(d1);
        System.out.println(c1);
        System.out.println(b2);
    }
}

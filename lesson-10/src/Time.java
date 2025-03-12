public class Time<T> {

    public static void main(String[] args) {
        test(new Time<Integer>());
        test2(new Time<Object>());
    }

    /**
     * 参数的泛型只能是 Number 或者其子类，Number，Byte，short，Long
     * Integer，Float，Double
     * @param time
     */
    public static void test(Time<? extends Number> time) {

    }

    /**
     * 参数的泛型只能是 String 或者其父类，String 和 Object
     * @param time
     */
    public static void test2(Time<? super String> time){

    }
}

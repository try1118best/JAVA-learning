import java.util.*;

public class Test {
    public static void main1(String[] args) {
        TreeSet set = new TreeSet();
        set.add(1);
        set.add(3);
        set.add(6);
        set.add(2);
        set.add(5);
        set.add(4);
        set.add(1);
        System.out.println("length:" + set.size());
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        set.remove(5);
        System.out.println("*****************************");
        iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main2(String[] args) {
        TreeSet set = new TreeSet();
        set.add(new A(1));
        set.add(new A(3));
        set.add(new A(6));
        set.add(new A(2));
        set.add(new A(4));
        set.add(new A(5));
        set.add(new A(1));
        System.out.println("length:" + set.size());
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    //HashMap：存储一组无序，key不可重复，但value可重复的元素。key重复直接覆盖value
    public static void main3(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("h","Hello");
        hashMap.put("w","World");
        hashMap.put("j","Java");
        hashMap.put("s","JavaSE");
        hashMap.put("m","JavaME");
        hashMap.put("e","JavaEE");
        hashMap.put(hashMap,hashMap);
        System.out.println(hashMap);
        hashMap.remove("e");
        System.out.println(hashMap.toString());
        hashMap.put("m","Model");
        hashMap.put("m1","Model");
        System.out.println(hashMap);
        System.out.println(hashMap.containsKey("m2"));
        System.out.println(hashMap.containsValue("Model1"));
        Set set = hashMap.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("********************************");
        Collection values = hashMap.values();
        Iterator iterator1 = values.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
    }

    //TreeMap：存储一组有序，key不可重复，但value可以重复的元素，可以按照key来排序
    public static void main4(String[] args) {
        TreeMap treeMap  = new TreeMap();
        treeMap.put(new A(3), "Java");
        treeMap.put(new A(5), "JavaME");
        treeMap.put(new A(1), "Hello");
        treeMap.put(new A(6), "JavaEE");
        treeMap.put(new A(2), "World");
        treeMap.put(new A(4), "JavaSE");
        Set set = treeMap.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            Object value = treeMap.get(key);

            System.out.println(key + "-" + value);
        }
    }

    //Collections工具类
    public static void main5(String[] args) {
        ArrayList list = new ArrayList();
        list.add("Hello");
        list.add("World");
        System.out.println(list);
        Collections.addAll(list,"Java","JavaSE","JavaME");
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);
        Collections.swap(list,1,3);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        Collections.replaceAll(list,"Java","Collections");
        System.out.println(list);
    }

    //泛型
    public static void main6(String[] args) {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add("Hello");
        for (int i = 0; i < list.size(); i++) {
            int num = (int) list.get(i);
            System.out.println(num);
        }
    }

    //泛型
    public static void main7(String[] args) {
        ShiJian<Integer> time1 = new ShiJian<>();
        time1.setValue(10);
        System.out.println(time1.getValue());
        ShiJian<String> time2 = new ShiJian<>();
        time2.setValue("十点");
        System.out.println(time2.getValue());
    }

    //在定义一个类时可以同时指定多个泛型标识
    public static void main8(String[] args) {
        Time1<String,Integer,Float> time =new Time1<>();
        time.setHour("十点");
        time.setMinute(10);
        time.setSecond(10.0f);
        System.out.println("现在的时间是：" + time.getHour() + ":" + time.getMinute() +
                ":" + time.getSecond());
    }

    public static void main9(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        test(list1);
        test(list2);
    }

    //泛型接口
    public static void main(String[] args) {
        MyInterface<String> myInterface = new MyInterfaceImpl<String>("接口");
        System.out.println(myInterface.getValue());
        MyInterface myInterface1 = new MyInterfaceImpl2("接口");
        System.out.println(myInterface1.getValue());
    }

    public static void test(ArrayList<?> list) {
        System.out.println(list);
    }
}



class A implements Comparable { //实现Comparable接口 才有比较的能力
    private int num;

    public A(int num) {
        this.num = num;
    }

    /*
     * A.compareTo(B)
     * 返回值：
     * 1表示A大于B
     * 0表示A等于B
     * -1表示A小于B
     **/
    @Override
    public int compareTo(Object o) {
        A a = (A) o;
        if (this.num > a.num) { //升序降序控制
            return 1;
        } else if (this.num == a.num) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "A{" +
                "num=" + num +
                '}';
    }
}

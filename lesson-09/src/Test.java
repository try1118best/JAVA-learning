import java.util.*;

public class Test {
//    public static void main1(String[] args) {
//        Account account = new Account();
//        Thread thread1 = new Thread(account);
//        Thread thread2 = new Thread(account);
//        thread1.start();
//        thread2.start();
//    }

    public static void main2(String[] args) {
        TimeLock timeLock = new TimeLock();
        new Thread(timeLock, "线程1").start();
        new Thread(timeLock, "线程2").start(); //注意是异步执行
    }

    public static void main3(String[] args) {
        Container container = new Container();
        Producer producer = new Producer(container);
        Consumer consumer = new Consumer(container);
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
    }

    public static void main4(String[] args) {
        TicketRunnable ticketRunnable = new TicketRunnable();
        new Thread(ticketRunnable, "窗口A").start();
        new Thread(ticketRunnable, "窗口B").start();
        new Thread(ticketRunnable, "窗口C").start();
    }

    public static void main5(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("Hello");
        arrayList.add("World");
        arrayList.add("JavaSE");
        arrayList.add("JavaME");
        arrayList.add("JavaEE");
        System.out.println(arrayList);
        System.out.println(arrayList.size());
        System.out.println(arrayList.contains("Hello"));
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        arrayList.remove("Hello");
        arrayList.remove(0);
        System.out.println("************************");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        arrayList.add(1, "Java");
        System.out.println("************************");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        arrayList.set(2, "ArrayList");
        System.out.println("************************");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        System.out.println("************************");
        System.out.println(arrayList.indexOf("JavaEE"));
        List list = arrayList.subList(1, 3);
        System.out.println("************************");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void main6(String[] args) {
        HashSet set = new HashSet();
        set.add("Hello");
        set.add("World");
        set.add("Java");
        set.add("Hello");
        System.out.println(set.size());
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        set.remove("World");
        System.out.println("删除之后的遍历");
        iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main7(String[] args) {
        LinkedHashSet set = new LinkedHashSet();
        set.add("Hello");
        set.add("World");
        set.add("Java");
        set.add("Hello");
        System.out.println(set.size());
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        set.remove("World");
        System.out.println("删除之后的遍历");
        iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        LinkedHashSet set=new LinkedHashSet();
        set.add(new A(1));
        set.add(new A(1));
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

class A{
    private int num;

    public A(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "A{" +
                "num=" + num +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        A a = (A) o;
        return a.num == this.num;
    }

    @Override
    public int hashCode() {
        return num;
    }
}



public class Test {
    public static void main1(String[] args) {
        new Thread(()->{                            //lambda表达式
            for (int i = 0; i < 100; i++) {
                System.out.println("+++++++++++" + i);
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println(i + "===========");
            }
        }).start();
    }

    public static void main2(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        MyThread2 myThread2= new MyThread2();
        myThread2.start();
    }

    public static void main3(String[] args) {
        MyRunnable1 myRunnable1 = new MyRunnable1();
        MyRunnable2 myRunnable2 = new MyRunnable2();
        MyRunnable3 myRunnable3 = new MyRunnable3();
        Thread thread1 = new Thread(myRunnable3);//1.创建一个原生的线程对象，并将任务分配给线程对象
        Thread thread2 = new Thread(myRunnable1);
        Thread thread3 = new Thread(myRunnable2);
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void main(String[] args) {
        new Thread(new MyRunnable()).start();

    }
}

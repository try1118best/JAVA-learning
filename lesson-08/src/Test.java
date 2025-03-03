public class Test {
//    public static void main1(String[] args) {
//        Account account = new Account();
//
//        Thread thread1 = new Thread(account);
//        thread1.setName("张三");
//        thread1.start();
//
//        Thread thread2 = new Thread(account);
//
//
//        thread2.start();
//     }

//    public static void main2(String[] args) {
//        for (int i = 0; i < 50; i++) {
//            new Thread(() -> {
//                SingletonDemo.getInstance();
//            }).start();
//        }
//    }

    public static void main(String[] args) {
        DeadLockRunnable deadLockRunnable1 = new DeadLockRunnable();
        deadLockRunnable1.num = 1;
        DeadLockRunnable deadLockRunnable2 = new DeadLockRunnable();
        deadLockRunnable2.num = 2;

        Thread thread1 = new Thread(deadLockRunnable1);
        thread1.setName("张三");
        Thread thread2 = new Thread(deadLockRunnable2);
        thread2.setName("李四");
        thread1.start();
        try {
            Thread.sleep(99);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}

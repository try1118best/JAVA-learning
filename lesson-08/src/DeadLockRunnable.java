public class DeadLockRunnable implements Runnable {
    public int num;
    //定义两个静态的对象
    private static Chopsticks chopsticks1 = new Chopsticks();
    private static Chopsticks chopsticks2 = new Chopsticks();

    @Override
    public void run() {
        //张三拿到筷子
        if (num == 1) {
            System.out.println(Thread.currentThread().getName() +
                    "获取到chopsticks1,等待获取chopstick2");
            synchronized (chopsticks1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (chopsticks2) {
                    System.out.println(Thread.currentThread().getName() + "用餐完毕");
                }
            }
        }
        if (num == 2) {
            System.out.println(Thread.currentThread().getName() +
                    "获取到chopsticks2,等待获取chopsticks1");
            synchronized (chopsticks2) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (chopsticks1) {
                    System.out.println(Thread.currentThread().getName() + "用餐完毕");
                }
            }
        }
    }
}

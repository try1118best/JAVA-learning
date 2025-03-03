public class Account implements Runnable {
    private static int num;

    @Override
    public synchronized void run() {
        System.out.println(1);
        System.out.println(2);
        synchronized (Account.class){
            num++;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "是当前的第" + num + "位访客");
        }

        System.out.println(3);
        System.out.println(4);
    }
}

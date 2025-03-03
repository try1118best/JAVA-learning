import java.util.concurrent.locks.ReentrantLock;

public class Account implements Runnable {
    private static int num;
    private ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        reentrantLock.lock();
        num++;
        System.out.println(Thread.currentThread().getName() + "是当前的第" + num + "个访客");
        //reentrantLock.unlock();
    }
}

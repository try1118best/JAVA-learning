import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeLock implements Runnable {
    public ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (reentrantLock.tryLock(3, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + "获取到了锁");
                Thread.sleep(5000);
            } else {
                System.out.println(Thread.currentThread().getName() + "无法获取锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (reentrantLock.isHeldByCurrentThread()) {
                reentrantLock.unlock();
            }
        }
    }
}

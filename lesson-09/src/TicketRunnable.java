public class TicketRunnable implements Runnable {
    //剩余球票
    public int count1 = 15;
    //已售出球票
    public int count2 = 0;

    @Override
    public void run() {
        while (count1 > 1) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count1 == 0) return;
            synchronized (TimeLock.class) {
                count1--;
                count2++;
                if (count1 == 0) {
                    System.out.println(Thread.currentThread().getName() + "售出了第" + count2 +
                            "张球票，球票已售罄");
                } else {
                    System.out.println(Thread.currentThread().getName() + "售出了第" + count2 +
                            "张球票，剩余：" + count1 + "张球票");
                }
            }
        }
    }
}

public class SynchronizedTest {
    public static void main1(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread=new Thread(()->{
                test();
            });
            thread.start();
        }
    }

    public synchronized static void test() {
        System.out.println("start....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end....");

    }
}

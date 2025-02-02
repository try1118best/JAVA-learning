public class MyRunnable1 implements Runnable{//实现类
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("........打水");
        }
    }
}

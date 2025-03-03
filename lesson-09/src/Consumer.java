public class Consumer implements Runnable{
    private Container container;

    public Consumer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            this.container.pop();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

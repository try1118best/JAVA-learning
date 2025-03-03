public class Producer implements Runnable {
    private Container container = null;

    public Producer(Container container) {
        this.container = container; 
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            Hamburger hamburger=new Hamburger(i);
            this.container.push(hamburger);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

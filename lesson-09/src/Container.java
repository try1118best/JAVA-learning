public class Container {
    public Hamburger[] array = new Hamburger[6];
    public int index = 0;

    //向容器中添加汉堡
    public synchronized void push(Hamburger hamburger) {
        while (index == array.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();//让该线程恢复执行
        array[index] = hamburger;
        index++;
        System.out.println("生产了一个汉堡：" + hamburger);
    }

    //从容器中取走汉堡
    public synchronized Hamburger pop() {
        while (index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();//让该线程恢复执行
        index--;
        System.out.println("消费了一个汉堡" + array[index]);
        return array[index];
    }
}

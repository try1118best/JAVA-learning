public class SingletonDemo {
    private static volatile SingletonDemo instance;

    private SingletonDemo() {
        System.out.println("创建一个对象");
    }

    public synchronized static SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }
}

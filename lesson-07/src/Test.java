public class Test {
    public static void main1(String[] args) {
        new Thread(() -> {                            //lambda表达式
            for (int i = 0; i < 100; i++) {
                System.out.println("+++++++++++" + i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i + "===========");
            }
        }).start();
    }

    public static void main2(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        MyThread2 myThread2 = new MyThread2();
        myThread2.start();
    }

    public static void main3(String[] args) {
        MyRunnable1 myRunnable1 = new MyRunnable1();
        MyRunnable2 myRunnable2 = new MyRunnable2();
        MyRunnable3 myRunnable3 = new MyRunnable3();
        Thread thread1 = new Thread(myRunnable3);//1.创建一个原生的线程对象，并将任务分配给线程对象
        Thread thread2 = new Thread(myRunnable1);
        Thread thread3 = new Thread(myRunnable2);
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void main4(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i + "=============");
            }
        });
        thread1.start();
        try {
            thread1.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("++++++++++++" + i);
            }
        });
        thread2.start();
    }

    public static void main5(String[] args) {
        // 初始休眠时间
        int initialT = 0;
        new Thread(() -> {
            // 外层循环，执行 10 次
            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    int t = initialT;
                    try {
                        // 内层循环，执行 5 次
                        for (int i1 = 0; i1 < 5; i1++) {
                            t += 1000;
                            System.out.println("Thread is sleeping for " + t + " milliseconds.");
                            Thread.sleep(t);
                            t -= 1000;
                        }
                    } catch (InterruptedException e) {
                        // 处理线程中断异常
                        System.err.println("Thread was interrupted: " + e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("--------Thread" + i);
            }
        }).start();
    }

    public static void main6(String[] args) {
        int tgap = 0;
        new Thread(() -> {
            for (int k = 0; k < 20; k++) {
                int t = tgap;
                try {
                    for (int i = 0; i < 20; i++) {
                        t += 1000;
                        Thread.sleep(t);
                        k++;
                        t -= 1000;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("--------Thread" + k);
            }
        }).start();
    }

    public static void main7(String[] args) {
        int tgap = 0;
        new Thread(() -> {
            for (int k = 0; k < 20; k++) {
                // 计算当前的休眠时间，随着外层循环次数增加而递增
                int t = tgap + 1000;
                try {
                    System.out.println("Thread " + k + " is going to sleep for " + t + " milliseconds.");
                    // 线程休眠
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    // 打印更详细的异常信息
                    System.err.println("Thread " + k + " was interrupted: " + e.getMessage());
                    // 重新设置中断状态
                    Thread.currentThread().interrupt();
                }
                System.out.println("--------Thread" + k);
            }
        }).start();
    }

    //倒计时
    public static void main8(String[] args) {
        new Thread(()->{
            for (int i = 10; i > 0; i--) {
                int gap_t=1000;
                try {
                    Thread.sleep(gap_t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }).start();
    }

    public static void main(String[] args) {
        Thread thread=new Thread(()->{
            for (int i = 0; i < 20; i++) {
                System.out.println(i + "-----------joinRunnable");
            }
        });
        thread.start();

        for (int i = 0; i < 100; i++) {
            if(i==10){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main+++++++++++++" + i);
        }

    }
}

import java.util.concurrent.atomic.AtomicInteger;

public class MyThreads {
    private Object object = new Object();
    private static final int COUNT = 20;
    public static void main(String args[]) throws InterruptedException{
        MyThreads t = new MyThreads();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for(int i=0;i< COUNT;i++){
           Thread tt =new Thread(t.new MyThread(i,atomicInteger));
           tt.setName("Thread " + i);
           tt.start();

       }

    }


    private class MyThread implements Runnable{
        private final int threadCount;
        AtomicInteger atomicInteger;
        public MyThread(int threadCount,AtomicInteger value) {
            super();
            this.threadCount = threadCount;
            this.atomicInteger = value;
        }


        public void run() {
            while (this.atomicInteger.get() < COUNT) {

                synchronized (object) {

                    if (this.atomicInteger.get()  == this.threadCount) {

                        if(this.atomicInteger.get() < COUNT)
                            System.out.println(Thread.currentThread()+"  "+
                                    this.atomicInteger.incrementAndGet());
                    }
                }
            }
        }
    }
}

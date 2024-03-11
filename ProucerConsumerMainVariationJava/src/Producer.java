import java.util.Random;

class Producer extends Thread {
    private final int itemNumbers;
    private final int producerId;
    private final Manager manager;

    public Producer(int itemNumbers, int producerId, Manager manager) {
        this.itemNumbers = itemNumbers;
        this.producerId = producerId;
        this.manager = manager;
    }

    @Override
    public void run() {
            try {
                while (manager.producerSize.getAndIncrement() < itemNumbers) {
                    manager.full.acquire();
                    manager.access.acquire();

                    Random rand = new Random();
                    String item = String.valueOf(rand.nextInt(0, 200));
                    manager.addItem("item " + item);
                    System.out.println("---Producer " + producerId + ": Added item " + item);

                    manager.access.release();
                    manager.empty.release();
                    Thread.sleep(500);
                }
            }
            catch(InterruptedException e){
                    e.printStackTrace();
            }
        System.out.println("Producer " + producerId + " finish.");
    }
}


class Consumer extends Thread {
    private final int itemNumbers;
    private final int consumerId;
    private final Manager manager;

    public Consumer(int itemNumbers, int consumerId, Manager manager) {
        this.itemNumbers = itemNumbers;
        this.consumerId = consumerId;
        this.manager = manager;
    }

    @Override
    public void run() {
        try {
            while (manager.consumerSize.getAndIncrement() < itemNumbers) {
                manager.empty.acquire();
                manager.access.acquire();

                String item = manager.getItem();
                System.out.println("Consumer " + consumerId + ": Took " + item);

                manager.access.release();
                manager.full.release();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Consumer " + consumerId + " finish.");
    }

}
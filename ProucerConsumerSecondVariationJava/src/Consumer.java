class Consumer implements Runnable {

    private final int consumerId;
    private final int startIndex;
    private final int endIndex;
    private final Manager manager;

    public Consumer(int itemNumbers, int consumerId, int startIndex, int endIndex, Manager manager) {
        this.consumerId = consumerId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.manager = manager;

        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            try {
                manager.empty.acquire();
                manager.access.acquire();

                String item = manager.getItem();
                System.out.println("Consumer " + consumerId+": Took " + item);

                manager.access.release();
                manager.full.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Consumer " + consumerId + " finish.");
    }
}
class Producer implements Runnable{
    private final int producerId;
    private final int startIndex;
    private final int endIndex;
    private final Manager manager;

    public Producer(int itemNumbers, int producerId, int startIndex, int endIndex, Manager manager) {
        this.producerId = producerId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.manager = manager;

        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            try {
                manager.full.acquire();
                manager.access.acquire();

                manager.addItem("item " + i);
                System.out.println("---Producer " + producerId + ": Added item " + i);

                manager.access.release();
                manager.empty.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Producer " + producerId + " finish.");
    }
}


public class Main {
    public static void main(String[] args) throws InterruptedException {
        int storageSize = 2;
        int itemNumbers = 6;
        int producerCount = 2;
        int consumerCount = 4;

        Manager manager = new Manager(storageSize);

        for (int i = 0; i < producerCount; i++) {
            new Producer(itemNumbers,i+1, manager).start();
        }

        for (int i = 0; i < consumerCount; i++) {
            new Consumer(itemNumbers,i+1, manager).start();
        }
    }
}


public class Main {
    public static void main(String[] args) {
        int storageSize = 4;
        int itemNumbers = 10;
        int producerCount = 3;
        int consumerCount = 1;

        Manager manager = new Manager(storageSize);

        int chunkSize = itemNumbers / producerCount;
        for (int i = 0; i < producerCount; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == producerCount - 1) ? itemNumbers : (i + 1) * chunkSize;
            new Producer(itemNumbers,i,startIndex,endIndex, manager);
        }

        chunkSize = storageSize / consumerCount;
        for (int i = 0; i < consumerCount; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == consumerCount - 1) ? itemNumbers : (i + 1) * chunkSize;
            new Consumer(itemNumbers,i,startIndex,endIndex, manager);
        }
    }
}
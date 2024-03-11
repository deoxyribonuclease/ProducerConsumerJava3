
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

class Manager {

    public Semaphore access;
    public Semaphore full;
    public Semaphore empty;

    private ArrayList<String> storage = new ArrayList<>();

    public Manager(int storageSize) {
        access = new Semaphore(1);
        full = new Semaphore(storageSize);
        empty = new Semaphore(0);
    }

    public  void addItem(String item) {
        storage.add(item);
    }

    public  String getItem() {
        return storage.remove(0);
    }
}
package interface_form.queue;

public class App {
    public static void main(String[] args) {
        ArrayQueue<Integer> intQueue = new ArrayQueue<>();
        intQueue.offer(1);
        intQueue.offer(2);
        intQueue.offer(3);
        intQueue.offer(4);
        intQueue.offer(5);
        System.out.println(intQueue.size());
        while (true){
            System.out.println(intQueue.poll());
            if (intQueue.size() == 0)return;
        }
    }
}

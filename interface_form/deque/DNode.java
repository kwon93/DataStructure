package interface_form.deque;

public class DNode<E> {

    E data;
    DNode<E> next;
    DNode<E> prev;

    public DNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

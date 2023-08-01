package interface_form.queue;

import interface_form.Queue;

import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements Queue<E> {

    private Qnode<E> head;
    private Qnode<E> tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean offer(E value) {
        Qnode<E> newNode = new Qnode<>(value);

        //비어있을 경우 (첫번째 요소일 경우)
        if (size == 0){
            head = newNode;
        }else { //그밖에 경우
            tail.next = newNode;
        }

        tail = newNode;
        size++;
        return true;
    }

    @Override
    public E poll() {
        //삭제할 요소가 없을 경우 null반환
        if (size == 0){
            return null;
        }
        //삭제될 요소의 데이터를 반환하기 위한 임시 변수
        E pollElement = head.data;

        Qnode<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        head = nextNode;
        size--;

        return pollElement;
    }

    public E remove(){
        E pollElement = poll();

        //삭제할 요소가 없을 경우 예외 처리
        if (size == 0){
            throw new NoSuchElementException();
        }

        return pollElement;
    }

    @Override
    public E peek() {
        if (size == 0){
            return null;
        }
        return head.data;
    }

    public E element(){
        E element = peek();

        if (size == 0){
            throw new NoSuchElementException();
        }

        return element;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(Object value){
        /**
         * head 데이터부터 x가 null이 될 때 까지 value랑 x의 데이터(x.data)랑 같은지를 비교하고 같을 경우 true 반환
         *
         */
        for (Qnode<E> x = head; x != null; x = x.next){
            if (value.equals(x.data)){
                return true;
            }
        }

        return false;
    }

    public void clear(){
        for (Qnode<E> x = head; x != null;){
            Qnode<E> next = x.next;
            x.data = null;
            x.next = null;
            x = next;
        }
        size = 0;
        head = tail = null;
    }
}

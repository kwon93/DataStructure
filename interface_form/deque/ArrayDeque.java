package interface_form.deque;

import interface_form.Queue;

import java.util.NoSuchElementException;

public class ArrayDeque<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 64;

    private Object[] array;
    private int size;

    private int front;
    private int rear;

    //생성자1 (초기 용적을 할당하지 않을 경우)
    public ArrayDeque(){
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public ArrayDeque(int capacity){
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    private void resize(int newCapacity){

        int arrayCapacity = array.length; //현재 용적의 크기

        Object[] newArray = new Object[newCapacity];

        for (int i = 1, j = front + 1; i <= size; i++, j++){
            newArray[i] = array[j % arrayCapacity];
        }

        this.array = null;
        this.array = newArray;

        front = 0;
        rear = size;
    }


    @Override
    public boolean offer(E item) {
        return offerLast(item);
    }

    private boolean offerLast(E item) {
        //용적이 가득 찼을 경우
        if ((rear + 1) % array.length == front){
            resize(array.length * 2);
        }

        rear = (rear + 1) % array.length;

        array[rear] = item;
        size++;

        return true;
    }

    public boolean offerFirst(E item){
        //용적이 가득 찼을 경우
        if ((front - 1 + array.length) % array.length == rear){
            resize(array.length * 2);
        }

        array[front] = item;
        front = (front - 1 + array.length) % array.length;
        size++;

        return true;
    }



    @Override
    public E poll() {
        return pollFirst();
    }

    public E pollFirst() {

        if (size == 0){
            return null;
        }

        front = (front + 1) % array.length;

        @SuppressWarnings("unchecked")
        E item = (E) array[front];

        array[front] = null;
        size--;

        if (array.length > DEFAULT_CAPACITY && size < (array.length / 4)){

            //
            resize(Math.max(DEFAULT_CAPACITY, array.length/2));
        }

        return item;
    }

    public E remove(){
        return removeFirst();
    }

    private E removeFirst() {
        E item = pollFirst();

        if (item == null){
            throw new NoSuchElementException();
        }

        return item;
    }

    public E pollLast(){
        if (size == 0){
            return null;
        }

        @SuppressWarnings("unchecked")
        E item = (E) array[rear];

        array[rear] = null;

        rear = (rear - 1 + array.length) % array.length;
        size--;

        if (array.length > DEFAULT_CAPACITY && size < (array.length / 4)){

            resize(Math.max(DEFAULT_CAPACITY, array.length/2));
        }

        return item;
    }

    public E removeLast(){
        E item = pollLast();
        if (item == null){
            throw new NoSuchElementException();
        }

        return item;
    }
    


    @Override
    public E peek() {
        return peekFirst();
    }


}

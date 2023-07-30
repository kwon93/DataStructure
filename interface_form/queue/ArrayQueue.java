package interface_form.queue;

import interface_form.Queue;

public class ArrayQueue<E> implements Queue<E> {


    private static final int DEFAULT_CAPACITY = 64;

    private Object[] array; //요소를 담을 배열
    private int size; //요소의 개수

    private int front; //시작 인덱스를 가리키는 변수 (빈 공간이여야 한다.)
    private int rear; //마지막 요소의 인덱스를 가리키는 변수

    public ArrayQueue() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    //용적할당을 하고 초기화 할 경우
    public ArrayQueue(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    private void resize(int newCapacity){

        int arrayCapacity = array.length; //현재 용적 크기

        Object[] newArray = new Object[newCapacity];

        /*
        * i = new array index
        * j = original array
        * index 요소 개수만큼 새 배열에 값 복사
        * */
        for (int i = 1, j = front + 1; i <= size ; i++,j++) {
            newArray[i] = array[j % arrayCapacity];
        }

        this.array = null;
        this.array = newArray;

        front = 0;
        rear = size;
    }


    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }
}

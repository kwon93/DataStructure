package interface_form.queue;

import interface_form.Queue;

import java.util.NoSuchElementException;

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
    public boolean offer(E item) {

        //용적이 가득 찼을 경우
        if ((rear + 1) % array.length == front){
            resize(array.length * 2);
        }
        rear = (rear + 1) % array.length;

        array[rear] = item;
        size++;
        return true;
    }

    /**
     * poll()과 remove()의 차이
     * poll() : return값이 null일 경우 null을 그대로 return
     * remove() : return값이 null일 경우 예외 처리
     */

    @Override
    public E poll() {
        //삭제할 요소가 없을 경우 null 반환
        if (size == 0) return null;

        front = (front + 1) % array.length;

        @SuppressWarnings("unchecked")
        E pollItem = (E) array[front]; //반환할 데이터 임시 저장.

        array[front] = null;
        size--;

        //용적이 최소 크기보다 크고 요소 개수보다 1/4 미만일 경우
        if (array.length > DEFAULT_CAPACITY && size < (array.length / 4)){
            //아무리 작아도 최소 용적 미만으로는 줄이지 않도록함.
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }

        return pollItem;

    }

    public E remove(){
        //remove는 poll을 호출해서 item이 null일 경우에만 예외를 던지면 됨.
        E item = poll();

        if (item == null){
            throw new NoSuchElementException();
        }
        return item;
    }

    /*
    * peek() 과 element() 의 차이점도
    * poll() 과 remove()와 같다.
    * null return의 차이
    * */
    @Override
    public E peek() {
        if (size == 0){
            return null;
        }

        @SuppressWarnings("unchecked")
        E item = (E) array[(front+ 1) % array.length];
        return item;
    }

    public E element(){
        E item = peek();
        if (item == null){
            throw  new NoSuchElementException();
        }
        return item;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }


    public boolean contains(Object value){
        int start = (front + 1) % array.length;

        for (int i = 0, idx = start; i < size; i++,idx =  (idx + 1)% array.length) {
            if (array[idx].equals(value)) return true;

        }
        return false;
    }
    
    public void clear(){
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }

        front = rear = size = 0;
    }



}

package interface_form.stack;

import interface_form.list.ArrayList;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> extends ArrayList<E> implements interface_form.Stack<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    private Object[] array; // 배열의 용량 , 용적
    private int size; // 배열안에 요소의 개수

    //공간할당 없이 초기화할 때
    public Stack() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }


    //공간할당을 하며 초기화 할때
    public Stack(int capacity){
        this.array = new Object[capacity];
        this.size = 0;
    }



    private void resize(){

        //빈 배열일 경우
        if (Arrays.equals(array, EMPTY_ARRAY)){
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        //공간이 꽉 찼을 경우
        int arrayCapacity = array.length;

        if (size == arrayCapacity){
            int newSize = arrayCapacity * 2;

            array = Arrays.copyOf(array, newSize);
            return;
        }

        //용적의 절반 미만으로 요소가 차지해 메모리가 낭비되고 있을 경우
        if (size < (arrayCapacity /2)){
            int newCapacity = (arrayCapacity /2);

            array = Arrays.copyOf(array, Math.max(DEFAULT_CAPACITY, newCapacity)); //최소용적 10 이하로는 떨어지지않게 Math.max 사용 (둘중 큰것을 return)
            return;
        }
    }

    @Override
    public E push(E item) {
        if (size == array.length){
            resize();
        }
        array[size] = item;
        size++;

        return item;
    }

    @Override
    public E pop() {
        //만약 삭제할 요소가 없다면 stack안에 아무 요소가 없으니 예외 발생시키기
        if (size == 0){
            throw new EmptyStackException();
        }

        @SuppressWarnings("unchecked")
        E popElement = (E) array[size -1]; //삭제할 element 임시 저장

        array[size -1] = null; // element 삭제

        size--;
        resize();

        return popElement;
    }


    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        //peek할 요소가 없다면 텅빈 stack이므로 예외 처리

        if (size == 0){
            throw new EmptyStackException();
        }

        return (E) array[size -1];
    }

    //Stack의 상단에서부터 몇번째 위치하는 element인지 찾아보기
    @Override
    public int search(Object value) {
        //찾는 value가 null인 경우
        if (value == null){
            for (int idx = size - 1; idx >= 0; idx--) {
                if (array[idx] == null){
                    return size - idx;
                }

            }
        }else{
            for (int idx = size - 1; idx >= 0 ; idx--) {
                if (array[idx].equals(value)){
                    return size - idx;
                }
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {

        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    public Object[] toArray(){
        return Arrays.copyOf(array, size);
    }

}

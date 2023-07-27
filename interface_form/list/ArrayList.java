package interface_form.list;

import interface_form.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E>, Cloneable {

    private static final int DEFAULT_CAPACITY = 10; // 최소 크기
    public static final Object[] EMPTY_ARRAY = {}; // 빈 배열

    private int size;

    //size는 요소의 개수 , 공간 할당과는 다른 개념

    Object[] array; //요소를 담을 배열

    public ArrayList() { //공간할당을 안하고 객체만 생성하고 싶을때
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    public ArrayList(int capacity) {  //공간할당을 하고 객체를 생성할 때
        this.size = 0;
        this.array = new Object[capacity];
    }


    private void resize(){
        int arrayCapacity = array.length;

        //별도로 용량을 초기화하지않고 생성했을 경우
        if (Arrays.equals(array, EMPTY_ARRAY)){
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        //용량이 꽉 찰 경우
        if (size == arrayCapacity){
            int newCapacity = arrayCapacity * 2;


            array = Arrays.copyOf(array, newCapacity);
            return;
        }

        //용적의 절반 미만으로 요소가 차지하고 있을 경우
        if(size < (arrayCapacity / 2)){
            int newCapacity = arrayCapacity / 2;

            array = Arrays.copyOf(array, newCapacity);
            return;
        }
    }







    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException(); //배열 크기를 넘어가거나 적을경우 예외 처리
        }
        if (index == size){
            addLast(value);
        }else {

            if (size == array.length){ //꽉 차 있다면 용적 재할당
                resize();
            }
            //한칸씩 뒤로 밀기
            for (int i = size; i > index ; i--) {
                array[i] = array[i -1];
            }

            array[index] = value; //index위치에 요소 할당
            size++;
        }
    }

    public void addLast(E value){

        //꽉차있는 상태라면 용적 재할당
        if(size == array.length){
            resize();
        }
        array[size] = value;
        size++;
    }





    public void addFirst(E value){
        add(0, value);
    }

    @SuppressWarnings("uncheked")
    @Override
    public E remove(int index) {
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException(); //배열 크기를 넘어가거나 적을경우 예외 처리
        }

        E element = (E) array[index]; //삭제될 요소를 임시로 담아두고
        array[index] = null; // null 로 만들어 삭제하기

        //삭제한 요소 뒤에 모든 요소를 한칸씩 당긴다.
        for (int i = index; i < size -1 ; i++) {
            array[i] = array[i = 1];
            array[i + 1] = null; // 맨뒤에 요소를 null로 만들어 놓기
        }
        size--;
        resize();
        return element;
    }


    @Override
    public boolean remove(Object value) {
        //삭제하고자 하는 요소의 인덱스 찾기
        int index = indexOf(value);

        if (index == -1){
            return false;
        }

        remove(index);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        //Object 타입에서 E타입으로 캐스팅 후 반환
        return (E) array[index];
    }

    @Override
    public void set(int index, E vlaue) {
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        else {
            array[index] = vlaue;
        }
    }

    @Override
    public boolean contains(Object vlaue) {

        //indexOf 활용
        if (indexOf(vlaue) >= 0){
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(Object value) {
        int i = 0;

        //value와 같은 객체(요소) 일 경우 i 반환
        for (i= 0; i < size; i++) {
            if (array[i].equals(value)){
                return i;
            }
        }
        //일치하는 것이 없을 경우 -1 반환
        return -1;
    }

    public int lastIndexOf(Object value){
        for (int i = size -1 ; i >= 0; i--) {
            if(array[i].equals(value)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        // 모든 공간을 null 처리 해준다
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        //새로운 객체 생성
        ArrayList<?> cloneList = (ArrayList<?>) super.clone();

        //새로운 객체의 배열도 생성해주어야함.
        cloneList.array = new Object[size];

        //배열에 값을 복사하기
        System.arraycopy(array,0,cloneList.array, 0, size);
        return cloneList;

    }

    public Object[] toArray(){
        return Arrays.copyOf(array, size);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a){
        if (a.length < size){
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        }

        System.arraycopy(array,0,a,0,size);
        return a;
    }
}

package interface_form.heap;

import java.util.Comparator;

/** 힙의 성질
 * 1. 왼쪽 자식 노드 인덱스 = 부모 노드 인덱스 × 2
 * 2. 오른쪽 자식 노드 인덱스 = 부모 노드 인덱스 × 2 + 1
 * 3. 부모 노드 인덱스 = 자식 노드 인덱스 / 2
 */

public class Heap<E> {

    private final Comparator<? super E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private Object[] array;

    //공간할당 없이 생성
    public Heap(){
        this(null);
    }

    public Heap(Comparator<? super E> comparator) {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
    }

    /**
     * 생성자는 크게 4가지로 나눔
     * 먼저 데이터(요소)의 개수를 예상할 수 있어 배열의 크기(용적)를 최적으로 하고 싶을 때 초기에 생성할 배열의 크기를 설정 해줄 수 있도록 만든 방법과
     * 사용자가 정렬 방법을 따로 넘겨주고자 할 때 쓸 수 있도록 Comparator을 받는 방법을 조합하여 4가지로 나눔
     */

    //공간할당을 하며 생성
    public Heap(int capacity){
        this(capacity, null);
    }

    public Heap(int capacity, Comparator<? super E> comparator) {
        this.array = new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }

    //받은 인덱스의 부모 노드 인덱스를 반환
    private int getParent(int index){
        return index / 2;
    }

    private int getLeftChild(int index){
        return index * 2;
    }

    private int getRightChild(int index){
        return index * 2 + 1;
    }

    private void resize(int newCapacity){
        Object[] newArray = new Object[newCapacity];

        for (int i = 1; i <= size ; i++) {
            newArray[i] = array[i];
        }

        this.array = null; //gc처리
        this.array = newArray;
    }

    public void add(E value){
        //배열 용적이 꽉 차있을 경우 용적을 두 배로 늘려준다.
        if (size + 1 == array.length){
            resize(array.length * 2);
        }

        siftUp(size + 1, value);
        size++;
    }

    private void siftUp(int idx, E target){
        //comparator가 존재할 경우 comparator를 인자로 넘겨준다.
        if (comparator != null){
            siftUpComparator(idx,target,comparator);
        }else {
            siftUpComparable(idx, target);
        }
    }

    //comparator를 이용한 sift-up
    @SuppressWarnings("unchecked")
    private void siftUpComparator(int idx,E target,Comparator<? super E> comp){

        //root노드보다 클 때 까지만 탐색한다.



    }

    private void siftUpComparable(int idx, E target){

    }
}

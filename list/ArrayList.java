package list;

import java.util.ListIterator;

//ArrayList Implementation
public class ArrayList<T> {
    private int size = 0; //몇개의 데이터가 list에 들어가있는지 나타내는 필드값.
    private Object[] elementData = new Object[100]; //JCF같은 경우는 배열의 크기는 자동으로 커지도록 프로그래밍되있음. 자동으로 크기를 늘리는 성질은 List의 본래 속성은 아님.

    public boolean addLast(Object element){
        elementData[size] = element; //element는 elementData의 0번째 값이 된다.
        size++; // size를 1 증가함. index는 1이됨. 또 addLast가 호출되면 index1에 값이 추가되는 방식

        return true;
    }

    public boolean addFirst(Object element){
        return add(0, element); //add()를 활용해 간단하게 구현. 0번 idx값을 고정적으로 보냄. first니까
    }

    public boolean add(int idx , Object element){
        for (int i = size-1; i >= idx; i--) { //마지막 인덱스번호가 idx 입력값까지 같아질 때 까지 반복문 수행
            elementData[i+1] = elementData[i]; //해당 요소의 인덱스를 1씩 올려줌으로서 공간을 만들어준다.
        }
        elementData[idx] = element; // 새로 들어오게된 입력값을 빈 인덱스자리에 넣어준다.
        size++; //마지막으로 arrayList의 크기를 늘려줌.
        return true;
    }

    @Override
    public String toString() {
        String str = "[";
        for (int i = 0; i < size; i++) {
            str += elementData[i];
            if(i < size -1){ //마지막 인덱스보다 i가 작다면 "," 를 붙임.
                str += ",";
            }
        }
        return str+"]";
    }

    public Object remove(int idx){
        Object removed = elementData[idx]; //삭제하기전에 삭제하려는값을 옮겨둔다.
        for (int i = idx +1; i < size -1 ; i++) {
            elementData[i -1] = elementData[i];
        }
        size--;
        elementData[size] = null;
        return removed; // 삭제 메서드는 삭제했던 값을 return해야 한다.
    }

    public Object removeFirst(){
        return remove(0);
    }

    public Object removeLast(){
        return remove(size-1);
    }

    public Object get(int idx){
        return elementData[idx]; //ArrayList 의 장점, 배열의 index로 접근하기때문에 데이터를 조회할때 빠르다.
    }

    public int size(){
        return size; //변수로 직접 접근하지못하게 막고 메서드를 사용
    }

    public int indexOf(Object o){
        for (int i = 0; i < size ; i++) {
            if(elementData[i] == o){
                return i;
            }
        }
        return -1; //찾는값이 없을 때
    }

    public ListIterator listIterator(){
        return new ListIterator();
    }

    class ListIterator{
        private int nextIdx = 0;
        public boolean hasNext(){

            return nextIdx < size;
        }
        public Object next(){
            return elementData[nextIdx++];
        }

        public Object previous(){
            return elementData[--nextIdx];
        }

        public boolean hasPrevious(){
            return nextIdx > 0; //nextIdx가 0보다 크다면 true가 출력
        }

        public void add(Object element){
            ArrayList.this.add(nextIdx++,element);
        }

        public void remove(Object element){
            ArrayList.this.remove(nextIdx-1);
            nextIdx--;
        }
    }
}

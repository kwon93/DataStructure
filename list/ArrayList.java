package list;

//ArrayList Implementation
public class ArrayList {
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
}

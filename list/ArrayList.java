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

    public boolean add(int idx,Object element){
        elementData[idx] = element;
        return true;
    }
}

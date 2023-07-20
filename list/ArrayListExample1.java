package list;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListExample1 {
    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);
        arrayList.add(1,50); //index 1에 50이라는 Integer Value를 add.
        System.out.println(arrayList); // 10,50,20,30,40 print

        arrayList.remove(2); //index 2 삭제
        System.out.println(arrayList); // 10,50,30,40
        System.out.println(arrayList.get(1));
        System.out.println(arrayList.size());

        //iteration 반복
        Iterator<Integer> it = arrayList.iterator(); //iterator: list 접근자
        while (it.hasNext()){ //다음값이 있다면 true
            int value = it.next(); //원시타입으로 autoUnboxing됨.
            System.out.println("value = " + value);
        }




    }
}

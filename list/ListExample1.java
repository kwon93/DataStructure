package list;

import java.util.ArrayList;

public class ListExample1 {
    public static void main(String[] args) {
        /*
        *  List의 특징
        *  1. 순서가 있음
        *  2. 중복을 허용함.
        *   비어있는 데이터가 없는 데이터의 연속
        * */

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);

        for (Integer integer : arrayList) {
            System.out.println(integer);
        }

        /*
        * List는 두 가지를 지원한다.
        * 1. ArrayList : 추가,삭제는 느리지만 조회는 빠르다.
        * 2. LinkedList : 추가 삭제는 빠르지만 조회가 느리다.
        * */

    }
}

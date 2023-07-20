package list;


public class Main {
    public static void main(String[] args) {
        ArrayList numbers = new ArrayList();
        numbers.addLast(10); //마지막 인덱스에 밸류 추가하기
        numbers.addLast(20);
        numbers.addLast(30);
        numbers.addLast(40);
//
//        System.out.println(numbers.add(1, 15));
//        System.out.println(numbers.get(1));
//        System.out.println(numbers.remove(1));
//        System.out.println(numbers);
//        System.out.println(numbers.removeFirst());
//        System.out.println(numbers.removeLast());
        System.out.println(numbers.size());
        System.out.println(numbers.indexOf(99)); // 99는 없기에 -1이 리턴됨.

//        for (int i = 0; i < numbers.size(); i++) {
//            System.out.println(numbers.get(i));
//        }

        ArrayList.ListIterator li = numbers.listIterator();
//        while (li.hasNext()){
//            System.out.println(li.next());
//
//        }
//        while (li.hasPrevious()){
//            System.out.println(li.previous());
//        }
        while (li.hasNext()){
            int number = (int)li.next();
            if(number == 30){
                li.add(35);
            }
            System.out.println(number);
        }
        System.out.println(numbers);


    }
}

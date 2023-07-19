package list;


public class Main {
    public static void main(String[] args) {
        ArrayList numbers = new ArrayList();
        numbers.addLast(10); //마지막 인덱스에 밸류 추가하기
        numbers.addLast(20);
        numbers.addLast(30);
        numbers.addLast(40);
        numbers.add(1, 15);
        numbers.addFirst(5);
        System.out.println(numbers);

    }
}

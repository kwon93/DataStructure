package array;

public class ArrayExample1 {
    //여러 데이터를 하나의 이름으로 그룹핑해서 관리하기 위한것 -> 자료 구조
    //데이터가 많을때 그룹 관리가 필요해진다 이럴 때 사용한는것이 배열
    public static void main(String[] args) {

        String[] student = new String[5];
        student[0] = "최진혁"; // 통틀어 element
        student[1] = "한이람"; // "한아람"은 value
        student[2] = "최유빈"; // 2 는 index
        student[3] = "한이은";
        student[4] = "김주한";

        System.out.println("student[] 배열의 갯수는 ? : "+student.length+" 개"); //배열의 크기를 구할 수 있다. -> 5개

        for (String students : student) { //iteration
            System.out.println(students);
            //배열은 반복문을 통해 쉽고 효율적으로 접근할 수 있다.
            //배열의 요소 하나하나를 다 접근.
        }

        /*
        * 배열의 단점
        *   1. 크기가 정해져 있다.
        *   2. 기능이 없다.
        *
        * 배열의 장점
        *   1. 크기가 정해져 있다. (가볍다.)
        *   2. 기능이 없다. (단순하다.)
        *
        * 배열은 빠르고 단순하다는 특징.
        * */
    }
}

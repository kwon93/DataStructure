package interface_form.list;

public class App {
    public static void main(String[] args) throws CloneNotSupportedException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);

        ArrayList<Integer> clone = (ArrayList<Integer>) list.clone();
        clone.add(20);

        for (int i = 0; i <list.size(); i++) {
            System.out.println(list.get(i)+"list");
        }
        for (int i = 0; i <clone.size(); i++) {
            System.out.println(clone.get(i)+"clone");
        }



    }
}

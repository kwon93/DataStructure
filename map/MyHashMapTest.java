package map;

public class MyHashMapTest {

    public static void main(String[] args) {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();

        myHashMap.put("foo", 1);
        myHashMap.put("bar", 2);
        Integer fooValue = myHashMap.get("foo");
        Integer barValue = myHashMap.get("bar");

        System.out.println("my_hash_map_test_foo_value>>>>>>  "+fooValue);
        System.out.println("my_hash_map_test_bar_value>>>>>>  "+barValue);

        myHashMap.remove("foo");

        System.out.println("my_hash_map_test_remove>>>>>>  "+myHashMap.size());
    }
}

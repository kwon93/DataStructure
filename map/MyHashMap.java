package map;


import java.util.Objects;

public class MyHashMap <K, V> {

    //기본 용량과 로드 팩터
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Node<K, V>[] table;
    private int size = 0;

    // Node Class 정의 (키와 값을 저장)
    static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public MyHashMap() {
        this.table = new Node[DEFAULT_CAPACITY];
    }

    // hash fucntion 해시코드로 배열 인덱스를 계산
    private int hash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % table.length;
    }

    // 키-값 추가
    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> newNode = new Node<>(key, value, null);
        Node<K, V> current = table[index];

        if (current == null) {
            table[index] = newNode;
        }else {
            Node<K, V> prev = null;
            while (current != null) {
                if (Objects.equals(current.key, key)){
                    current.value = value; // 키가 이미 존재하는 값이면 값을 업데이트
                    return;
                }
                prev = current;
                current = current.next;
            }
            prev.next = newNode;
        }
        size++;

        // loadFactor를 초과하면 resizing 진행
        if ((float) size / table.length >= LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = hash(key);
        Node<K , V> current = table[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null; // 존재하지 않는 키의 값 요청일 경우 null ~
    }


    // hash bucket 크기 증가
    private void resize() {
        int newCapacity = table.length * 2;
        Node[] newTables = new Node[newCapacity];

        for (Node<K, V> node : table) {
            while (node != null) {
                Node<K,V> next = node.next;
                int index = Math.abs(node.key.hashCode() % newCapacity);
                node.next = newTables[index];
                newTables[index] = node;
                node = next;
            }
        }
        table = newTables;
    }

    public void remove(K key) {
        int index = hash(key);
        Node<K, V> current = table[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

}

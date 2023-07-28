package interface_form.list;

import interface_form.List;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

    private Node<E> head; //노드 첫부분
    private Node<E> tail; //노드 마지막 부분
    private int size; //요소 개수


    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index){
        //범위 밖(잘못된 위치)일 경우 예외 처리
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node<E> h = head;

        for (int i = 0; i < index; i++) {
            h = h.next; // h 노드의 다음 노드를 h에 저장한다.
        }
        return h;
    }

    public void addFirst(E value){
        Node<E> newNode = new Node<E>(value); // 새로운 노드 생성
        newNode.next = head; // 새로운 노드의 다음노드로 head를 연결
        head = newNode; // 새로운 노드를 head로 변경
        size++;

        /**
         * 다음에 가리킬 노드가 없는 경우
         * 즉, 데이터에 새 노드밖에 없는 경우
         * 새로운 노드는 처음 시작 노드이자 마지막 노드가 된다.
         */
        if (head.next == null){
            tail = head;
        }
    }

    public void addLast(E value){
        Node<E> newNode = new Node<E>(value);

        if (size == 0){ // 첫번째 노드 일시에는
            addFirst(value); // addFirst 한다.
            return;
        }

        /**
         * 마지막 노드일시에는 현재 tail의 다음 노드가 새로운 노드를 가리키게하고
         * tail을 새로운 노드로 옮긴다.
         */
        tail.next = newNode;
        tail = newNode;
        size++;

    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        //추가하려는 index가 가장 앞에 추가하려는 경우 addFirst호출
        if (index == 0){
            addFirst(value);
            return;
        }
        //추가하려는 index가 마지막인 경우
        if (index == size){
            addLast(value);
        }
        Node<E> prevNode = search(index -1); //추가하려는 위치 이전의 노드
        Node<E> nextNode = prevNode.next; //추가하려는 위치 다음의 노드

        Node<E> newNode = new Node<>(value);

        /**
         * 이전 노드가 가리키는 노드를 끊은 뒤
         * 새 노드로 변경해준다.
         * 또한 새 노드가 가리키는 노드는 nextNode 로 설정해준다.
         */
        prevNode.next = null;
        prevNode.next = newNode;
        newNode.next = nextNode;
        size++;


    }

    public E remove() {
        Node<E> headNode = head;
        if (headNode == null) {
            throw new NoSuchElementException();
        }
        E element = headNode.data; //삭제하기전 노드를 반환하기위한 임시 변수

        Node<E> nextNode = head.next; // head의 다음 노드

        head.data = null;
        head.next = null;

        head = nextNode;
        size--;

        /**
         * 삭제된 요소가 리스트의 유일한 요소였을 경우
         * 그 요소는 head이자 tail이었으므로
         * 삭제되면서 tail도 가리킬 요소가 없기에
         * size가 0일 경우 tail도 null로 변환
         */
        if (size == 0) {
            tail = null;
        }
        return element;
    }

    @Override
    public E remove(int index){
        //삭제하려는 노드의 인덱스가 첫번째인 경우
        if (index == 0){
            remove();
        }

        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node<E> prevNode = search(index - 1); //삭제하는 노드의 전 노드
        Node<E> removeNode = prevNode.next; // 삭제하려는 노드
        Node<E> nextNode = removeNode.next; // 삭제 노드의 next 노드

        E element = removeNode.data;

        prevNode.next = nextNode; //링크 연결

        //삭제노드가 마지막 노드였다면 tail을 prev로 갱신
        if (removeNode.next == null){
            tail = prevNode;
        }

        //최종 삭제 진행
        removeNode.data = null;
        removeNode.next = null;
        size--;

        return element;

    }

    @Override
    public boolean remove(Object value) {

        Node<E> prevNode = head;
        boolean hasValue = false;
        Node<E> x = head;    // removedNode

        // value 와 일치하는 노드를 찾는다.
        for (; x != null; x = x.next) {
            if (value.equals(x.data)) {
                hasValue = true;
                break;
            }
            prevNode = x;
        }

        // 일치하는 요소가 없을 경우 false 반환
        if(x == null) {
            return false;
        }

        // 만약 삭제하려는 노드가 head라면 기존 remove()를 사용
        if (x.equals(head)) {
            remove();
            return true;
        }

        else {
            // 이전 노드의 링크를 삭제하려는 노드의 다음 노드로 연결
            prevNode.next = x.next;

            // 만약 삭제했던 노드가 마지막 노드라면 tail을 prevNode로 갱신
            if(prevNode.next == null) {
                tail = prevNode;
            }
            x.data = null;
            x.next = null;
            size--;
            return true;
        }
    }



    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        Node<E> search = search(index);
        search.data = null;
        search.data = value;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;

        for (Node<E> x = head; x != null ; x = x.next) {
            if (value.equals(x.data)){
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (Node<E> x = head; x != null;) {
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
        }
        head = tail = null;
        size = 0;
    }
}

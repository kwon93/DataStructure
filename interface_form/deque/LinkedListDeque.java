package interface_form.deque;

import interface_form.Queue;

import java.util.NoSuchElementException;

public class LinkedListDeque<E> implements Queue<E> {

    private DNode<E> head;
    private DNode<E> tail;
    private int size;

    public LinkedListDeque() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public boolean offerFirst(E value){
        DNode<E> newNode = new DNode<E>(value); //새 노드 생성
        newNode.next = head; //새 노드의 next참조를 현 head로 변경.

        if (head != null){
            head.prev = newNode;
        }

        head = newNode; //head가 가리키는 노드가 newNode로 만듬.
        size++;

        //다음에 가리킬 노드가 없는 경우 (즉, 데이터에 newNode밖에 없는 경우)
        if (head.next == null){
            tail = head;
        }
        return true;
    }

    public boolean offerLast(E value){

        //데이터가 없을 경우 offerFirst로 인자를 넘겨줌.
        if (size == 0){
            return offerFirst(value);
        }

        DNode<E> newNode = new DNode<>(value);

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public boolean offer(E value){
        return offerLast(value);
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    public E pollFirst(){
        //poll같은 경우는 remove와 달리 데이터가 없는경우 null을 return
        if (size == 0){
            return null;
        }

        E pollElement = head.data;

        DNode<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        //삭제하기전 데이터가 두개이상 있을 경우
        if (nextNode != null){
            nextNode.prev = null;
        }

        head = null;
        head = nextNode;
        size--;

        //poll한 데이터가 덱의 유일한 요소였을 경우
        if (size == 0){
            tail = null;
        }
        return pollElement;
    }

    public E remove(){
        return removeFirst();
    }

    public E removeFirst(){
        E element = poll();

        if (element == null){
            throw new NoSuchElementException();
        }
        return element;
    }

    public E pollLast(){
        if (size == 0){
            return null;
        }
        E pollElement = tail.data;

        DNode<E> prevNode = tail.prev;

        tail.data = null;
        tail.prev = null;

        if (prevNode != null){
            prevNode.next = null;
        }

        tail = null;
        tail = prevNode;
        size--;

        if (size == 0){
            head = null;
        }
        return pollElement;
    }

    public E removeLast(){
        E removeElement = pollLast();
        if (removeElement == null){
            throw new NoSuchElementException();
        }
        return removeElement;
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    public E peekFirst(){
        if (size == 0){
            return null;
        }
        return head.data;
    }

    public E peekLast(){
        if (size == 0){
            return null;
        }
        return tail.data;
    }

    public E element(){
        return getFirst();
    }

    public E getFirst(){
        E item = peek();
        if (item == null){
            throw new NoSuchElementException();
        }
        return item;
    }

    public E getLast(){
        E item = peekLast();
        if (item == null){
            throw new NoSuchElementException();
        }
        return item;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(Object value){

        for (DNode<E> x = head; x != null; x = x.next){
            if (value.equals(x.data)){
                return true;
            }
        }
        return false;
    }

    public void clear(){
        for (DNode<E> x = head; x != null;){
            DNode<E> next = x.next;

            x.data = null;
            x.next = null;
            x.prev = null;
            x = next;
        }

        size = 0;
        head = tail = null;
    }





}

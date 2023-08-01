package interface_form.queue;

import interface_form.list.Node;

public class Qnode<E> {

    E data;
    Qnode<E> next; // 다음 노드를 가리키는 역할을 하는 변수

    Qnode(E data){
        this.data = data;
        this.next = null;
    }
}

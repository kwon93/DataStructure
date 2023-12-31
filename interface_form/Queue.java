package interface_form;

/**
 *
 * 자바 Queue Interface
 * Queue 는 ArrayQueue, LinkedQueue,
 * Deque, PriorityQueue 에 의해 구현
 *
 */
public interface Queue <E>{

    /**
     * 큐의 가장 마지막에 요소를 추가.
     *
     * @param e 추가할 요소
     * @return 큐에 요소가 정상적으로 추가됬을경우 {@Code true}를 반환
     */
    boolean offer(E e);

    /**
     * 큐의 첫번째 요소를 삭제하고 삭제된 요소를 반환
     * @return 삭제된 요소 반환
     */
    E poll();


    /**
     * 큐의 첫 번째 요소를 삭제하지않고 반환
     * @return 큐의 첫번째 요소를 반환
     */
    E peek();
}

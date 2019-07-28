package ru.atom.list;

/**
 * Contains ref to next node, prev node and value
 */
public class ListNode<E> {
    public E data;
    ListNode<E> next;
    ListNode<E> prev;

    public ListNode() {
        this.data = null;
        this.next = this.prev = null;
    }

    public ListNode(E data) {
        this.data = data;
        this.next = this.prev = null;
    }
}

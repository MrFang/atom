package ru.atom.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class CustomLinkedList<E> implements List<E> {
    final ListNode<E> entry = new ListNode<E>();
    int size;

    public CustomLinkedList() {
        entry.next = entry.prev = entry;
        size = 0;
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
    public boolean contains(Object o) {

        for (E item : this) {
            if (item.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            ListNode<E> current = entry.next;

            @Override
            public boolean hasNext() {
                return current != entry;
            }

            @Override
            public E next() {
                E value = current.data;
                current = current.next;
                return value;
            }
        };
    }

    @Override
    public boolean add(E e) {
        ListNode<E> newNode = new ListNode<E>(e);

        newNode.prev = entry.prev;
        newNode.next = entry;
        entry.prev.next = newNode;
        entry.prev = newNode;

        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        ListNode<E> current = entry.next;

        while (current != entry) {

            if (current.data.equals(o)) {
                current.prev.next = current.next;
                current.next.prev = current.prev;

                size--;
            }

            current = current.next;
        }

        return true;
    }

    @Override
    public void clear() {
        entry.prev = entry.next = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        int ind;
        ListNode<E> current = entry.next;

        for (ind = 0; ind < index; ++ind) {
            current = current.next;
        }

        return current.data;
    }

    @Override
    public int indexOf(Object o) {
        ListNode<E> current = entry.next;

        for (int i = 0; i < size; ++i) {

            if (current.data.equals(o)) {
                return i;
            }

            current = current.next;
        }

        return -1;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E item : c) {
            add(item);
        }

        return true;
    }


    /*
      !!! Implement methods below Only if you know what you are doing !!!
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return true;
            }
        }
        return true;
    }

    /**
     * Do not implement
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    /**
     * Do not implement
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    /**
     * Do not implement
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    /**
     * Do not implement
     */
    @Override
    public void add(int index, E element) {
    }

    /**
     * Do not implement
     */
    @Override
    public E remove(int index) {
        return null;
    }

    /**
     * Do not implement
     */
    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    /**
     * Do not implement
     */
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    /**
     * Do not implement
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    /**
     * Do not implement
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * Do not implement
     */
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /**
     * Do not implement
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * Do not implement
     */
    @Override
    public E set(int index, E element) {
        return null;
    }
}

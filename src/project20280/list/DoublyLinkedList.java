package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private final E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }

    private final Node<E> head;
    private final Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // TODO
        Node<E> newest = new Node<>(e, pred, succ);
        pred.next = newest;
        succ.prev = newest;
        size++;
    }

    @Override
    public int size() {
        // TODO
        return size;
    }

    @Override
    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    @Override
    public E get(int i) {
        // TODO
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
        Node<E> current = head;
        for (int k = 0; k <= i; k++) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public void add(int i, E e) {
        // TODO
        if (i < 0 || i > size) throw new IndexOutOfBoundsException();
        Node<E> current = head;
        for (int k = 0; k < i; k++) {
            current = current.getNext();
        }
        addBetween(e, current, current.getNext());
    }

    @Override
    public E remove(int i) {
        // TODO
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
        Node<E> current = head.getNext();
        for (int k = 0; k < i; k++) {
            current = current.getNext();
        }
        return remove(current);
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.next;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        // TODO
        Node<E> pred = n.getPrev();
        Node<E> succ = n.getNext();
        pred.next = succ;
        succ.prev = pred;
        size--;
        return n.getData();
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.next.getData();
    }

    public E last() {
        // TODO
        if (isEmpty()) {
            return null;
        }
        return tail.getPrev().getData();
    }

    @Override
    public E removeFirst() {
        // TODO
        if (isEmpty()) return null;
        return remove(head.getNext());
    }

    @Override
    public E removeLast() {
        // TODO
        if (isEmpty()) return null;
        return remove(tail.getPrev());
    }

    @Override
    public void addLast(E e) {
        // TODO
        addBetween(e, tail.getPrev(), tail);
    }

    @Override
    public void addFirst(E e) {
        // TODO
        addBetween(e, head, head.getNext());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}
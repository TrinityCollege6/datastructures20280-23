package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        // TODO
        int size = 0;
        Node<E> current = head;

        while(current != null)
        {
            size ++;
            current = current.next;
        }
        return size;
    }

    //@Override
    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    @Override
    public E get(int position) {
        // TODO
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + position);
        }
        Node<E> current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public void add(int position, E e) {
        // TODO
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + position);
        }
        else {
            Node<E> current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            Node<E> newNode = new Node<>(e, current.next);
            current.next = newNode;
            size++;
        }
    }


    @Override
    public void addFirst(E e) {
        // TODO
        head = new Node<E>(e, head);
        size ++;
    }

    @Override
    public void addLast(E e) {
        // TODO
        Node<E> newest = new Node<>(e, null);
        if (head == null) {
            head = newest;
        } else {
            Node<E> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = newest;
        }
        size++;
    }

    @Override
    public E remove(int position) {
        // TODO
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + position);
        }
        if (position == 0) {
            return removeFirst();
        } else {
            Node<E> current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            Node<E> removedNode = current.next;
            current.next = removedNode.next;
            size--;
            return removedNode.element;
        }
    }

    @Override
    public E removeFirst() {
        // TODO
        if (isEmpty()) {
            return null;
        }
        Node<E> removedNode = head;
        head = head.next;
        size--;
        return removedNode.element;
    }

    @Override
    public E removeLast() {
        // TODO
        if (isEmpty()) {
            return null;
        }
        Node<E> current = head;
        Node<E> prev = null;
        while (current.next != null) {
            prev = current;
            current = current.next;
        }
        if (prev == null) {
            head = null;
        } else {
            prev.next = null;
        }
        size--;
        return current.element;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

//        for(Integer i : ll) {
//            System.out.println(i);
//        }
        /*
        ll.addFirst(-100);
        ll.addLast(+100);
        System.out.println(ll);

        ll.removeFirst();
        ll.removeLast();
        System.out.println(ll);

        // Removes the item in the specified index
        ll.remove(2);
        System.out.println(ll);
        
         */
    }
}

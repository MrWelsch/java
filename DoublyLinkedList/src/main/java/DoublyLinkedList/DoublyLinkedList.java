package DoublyLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Class to create a doubly linked list, which features various functions.
 *
 * @author Nico Welsch, Oliver Theobald
 * @version 1.0
 */
public class DoublyLinkedList<E> implements java.util.List<E> {

    private Node head;
    private Node tail;

    private int size;

    /**
     * Standard Constructor in which the initial values of head and tail are set to
     * null.
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Inner Class Node to create node objects.
     *
     * @author Nico Welsch, Oliver Theobald
     * @version 1.0
     */
    class Node {

        private E element;
        private Node next;
        private Node prev;

        /**
         * Constructor with which the node attributes are defined.
         *
         * @param prev    node previous to the element
         * @param element element to add
         * @param next    node after the element
         */
        public Node(Node prev, E element, Node next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        /**
         * @return the element
         */
        public E getElement() {
            return element;
        }

        /**
         * @param element the element to set
         */
        public void setElement(E element) {
            this.element = element;
        }

        /**
         * @return the next
         */
        public Node getNext() {
            return next;
        }

        /**
         * @param next the next to set
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * @return the prev
         */
        public Node getPrev() {
            return prev;
        }

        /**
         * @param prev the prev to set
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

    }

    /**
     * Adds an element to the list.
     *
     * @param element
     */
    @Override
    public boolean add(E element) {
        Node node = new Node(tail, element, null);
        if (tail != null) {
            tail.next = node;
        } else if (head == null) {
            head = node;
        }
        tail = node;
        size++;
        return true;
    }

    /**
     * Adds all elements from a collection.
     *
     * @param collection
     */
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        Object[] container = collection.toArray();
        for (int i = 0; i < container.length; i++){
            Node node = new Node(tail, (E)container[i], null);
            if (tail != null) {
                tail.next = node;
            } else if (head == null) {
                head = node;
            }
            tail = node;
            size++;
        }
        return true;
    }

    /**
     * Removes all nodes from the list and sets the size to zero.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size - 1; i++) {
            head = head.next;
            head.prev = null;
        }
        head = null;
        size = 0;
    }

    /**
     * Checks if the list contains the object in question.
     *
     * @param object
     */
    @Override
    public boolean contains(Object object) {
        Node current = head;
        boolean contains = false;
        for (int i = 0; i < size; i++) {
            if (current.getElement().equals(object)) {
                contains = true;
            } else {
                current = current.next;
            }
        }
        return contains;
    }

    /**
     * Returns the object at a certain index.
     *
     * @param index
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        Node current = head;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return (E) current.getElement();
    }

    /**
     * Returns the index of the object in question.
     *
     * @param object
     */
    @Override
    public int indexOf(Object object) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.getElement().equals(object)) {
                return i;
            } else {
                current = current.next;
            }
        }
        return -1; // returns -1 if the list does not contain the element.
    }

    /**
     * Checks if the list is empty.
     *
     * @return true, false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Searches through the list for the object in question and removes it.
     *
     * @param object
     * @return true
     */
    @Override
    public boolean remove(Object object) throws NoSuchElementException {
        Node current = head;
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (head.getElement().equals(object)) {
            head = head.next;
            head.prev = null;
            size--;
        } else {
            while (current.getElement().equals(object) == false) {
                current = current.next;
            }
            if (current.prev != null) {
                current.prev.setNext(current.next);
            }
            if (current.next != null) {
                current.next.setPrev(current.prev);
            }
            size--;
        }
        return true;
    }

    /**
     * Removes the node at a certain index.
     *
     * @param index
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        Node current = head;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
            head.prev = null;
            size--;
        } else {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            if (current.prev != null) {
                current.prev.setNext(current.next);
            }
            if (current.next != null) {
                current.next.setPrev(current.prev);
            }
            size--;
        }
        return (E) current.getElement();
    }

    /**
     * Changes the value of an element at a certain index.
     *
     * @param index
     * @param element new value
     */
    @Override
    public E set(int index, E element) {
        Node current = head;
        E previous;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        previous = current.getElement();
        current.setElement(element);
        return previous;
    }

    /**
     * Returns the list size.
     *
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Converts the list into an array.
     *
     * @param array
     * @return list as array
     */
    @Override
    public <T> T[] toArray(T[] array) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            array[i] = (T) current.getElement();
            current = current.next;
        }
        return array;
    }

    // --------------------------------------------- GET & SET
    // ----------------------------------------------

    /**
     * @return the head
     */
    public Node getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * @return the tail
     */
    public Node getTail() {
        return tail;
    }

    /**
     * @param tail the tail to set
     */
    public void setTail(Node tail) {
        this.tail = tail;
    }

    /**
     * Returns the list as a customized String.
     *
     * @return String
     */
    public String toString() {
        Node node = head;
        StringBuilder builder = new StringBuilder("[");
        while (node != null) {
            builder.append(node.element).append(",");
            node = node.next;
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }

    // --------------------------------------------- BONUS
    // --------------------------------------------------

    @Override
    public Iterator<E> iterator() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int arg0) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    // ------------------------------------- NOT TO BE IMPLEMENTED
    // -----------------------------------------

    @Override
    public void add(int arg0, E arg1) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int arg0, Collection<? extends E> arg1) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> arg0) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object arg0) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> arg0) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> arg0) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int arg0, int arg1) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

}

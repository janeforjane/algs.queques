/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node
    {
        Item item;
        Node next;
        Node previous;
    }

    private Node first = null;
    private Node last = null;

    private int size;


    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {

        if(first !=null) {
            return false;
        }
        return true;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {

        if(item == null) {
            throw new IllegalArgumentException();
        }

        Node n = new Node();
        n.item = item;
        size++;

        if(isEmpty()) {
            first = n;
            last = n;

        }else {
            n.next = first;
            n.previous = null;
            first.previous = n;
            first = n;
        }

    }

    // add the item to the back
    public void addLast(Item item) {

        if(item == null) {
            throw new IllegalArgumentException();
        }

        Node n = new Node();
        n.item = item;
        size++;

        if(isEmpty()) {
            first = n;
            last = n;

        }else {
            n.next = null;
            n.previous = last;
            last.next = n;
            last = n;
        }

    }

    // remove and return the item from the front
    public Item removeFirst() {

        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item item = null;


        if(size == 1) {
            item = first.item;
            first = null;
            last = null;

            size--;
            return item;
        }else {
            item = first.item;

            first.next.previous = null;
            first = first.next;

            size--;
            return item;
        }

    }

    // remove and return the item from the back
    public Item removeLast() {

        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item item = null;


        if(size == 1) {
            item = first.item;
            first = null;
            last = null;

            size--;
            return item;
        }else {
            item = last.item;

            last.previous.next = null;
            last = last.previous;

            size--;
            return item;
        }

    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {

            if (current.next == null) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

        Deque<String> deque = new Deque();

        deque.addFirst("somestring 1");

        deque.addLast("somestring 2");

        deque.addLast("somestring 3");

        deque.addFirst("somestring 0");

        System.out.println(deque.size());

        System.out.println(deque.isEmpty());

        System.out.println(deque.removeFirst());

        System.out.println(deque.removeLast());

    }
}

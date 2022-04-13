/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {


    private class Node
    {
        Item item;
        Node next;
        Node previous;
    }

    private Item[] array = null;

    private Node first = null;
    private Node last = null;

    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {

        if(array != null) {
            return false;
        }
        return true;
    }

    // return the number of items on the randomized queue
    public int size() {

        if(array != null) {
            return array.length;
        }
        return 0;
    }

    // add the item
    public void enqueue(Item item) {

        if(item == null) {
            throw new IllegalArgumentException();
        }

        int next = 0;

        if(array != null) {
            next = array.length;
            array[next] = item;
        }
    }

    // remove and return a random item
    public Item dequeue() {

        if (array == null) {
            throw new NoSuchElementException();
        }

        int uniform = StdRandom.uniform(array.length);

        Item item = array[uniform];
        array[uniform] = null;

        Item[] newarray = null;
        int j = 0;

        for (int i = 0; i < array.length; i++) {
            if(array[i] != null) {
                newarray[j] = array[i];
                j++;
            }
        }

        array = newarray;

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {

        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        int uniform = StdRandom.uniform(array.length);

        Item item = array[uniform];

        return item;

    }

    // return an independent iterator over items in random order
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

        RandomizedQueue rqueue = new RandomizedQueue();

        rqueue.enqueue("something 0");
        rqueue.enqueue("something 1");
        rqueue.enqueue("something 2");

        System.out.println(rqueue.size);

        System.out.println(rqueue.isEmpty());

        System.out.println(rqueue.dequeue());

        System.out.println(rqueue.sample());

        System.out.println(rqueue.size);

    }
}

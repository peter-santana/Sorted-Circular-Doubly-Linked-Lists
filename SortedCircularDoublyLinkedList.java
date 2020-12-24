package edu.uprm.cse.ds.sortedlist;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements SortedList<E> {

    private static class Node<T>{

        private T element;
        private Node<T> next;
        private Node<T> previous;

        public Node<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }

        public Node(){
            this.element = null;
            this.next = null;
            this.previous = null;
        }

        public Node(Node<T> N, Node<T> P, T e) {
            this.element = e;
            this.next = N;
            this.previous = P;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    private Node<E> header;
    private int size;

    public SortedCircularDoublyLinkedList() {
        this.size = 0;
        this.header = new Node<E>();
    }

    /* Adds a new element to the list in the right order by iterating
     * over the list comparable interface.
     */
    @Override
    public boolean add(E obj) {
        int count = 0;
        Node<E> temp = header;
        /* if the list is empty it adds the node at the start and sets
        previous as the header
         */
        if (isEmpty()) {
            header.setNext(new Node<E>(header, header, obj));
            header.setPrevious(header.getNext());
        }
        while (count < this.size()) {
            if (temp.getNext().getElement().compareTo(obj) < 0)
                temp = temp.getNext();
            else {
                addMiddle(temp, temp.getNext(), obj);
                break;
            }
            count ++;
        }
        if (count == this.size()) {
            addMiddle(temp, header, obj);
        }
        this.size++;
        return true;
    }

    /* returns the current size of the list */
    @Override
    public int size() {
        return this.size;
    }

    /* If the element is on the list, use remove function with firstIndex of the obj
    and return true
    * else, return false*/
    @Override
    public boolean remove(E obj) {
      int ind = firstIndex(obj);
      if(ind != -1) {
          remove(ind);
          return true;
      }
      return false;
    }

    /* Removes the node in the according index
     * Returns true if the element is erased, or an IndexOutBoundsException if index
     * position isn't on the list
     */
    @Override
    public boolean remove(int index) {
        if (isEmpty()) {
            return false;
        }
        if ((index < 0) || (index >= this.size())){
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            Node<E> temp = this.header.getNext();
            this.header.setNext(temp.getNext());


            //garbage collector
            temp.setElement(null);
            temp.setNext(null);
            temp.setPrevious(null);
            this.size--;
            return true;
        }
        else {
            Node<E> temp1 = nodeFinderIterator(index - 1);
            Node<E> temp2 = temp1.getNext();
            temp1.setNext(temp2.getNext());
            temp1.getNext().setPrevious(temp1);


            //garbage collector
            temp2.setNext(null);
            temp2.setPrevious(null);
            temp2.setElement(null);
            this.size--;
            return true;
        }

    }

    /* Removes all the nodes in the list that have the element in the parameter.
     * Returns the amount of elements erased.
     */
    @Override
    public int removeAll(E obj) {
        int count = 0;
        while(this.firstIndex(obj) >= 0){
            this.remove(obj);
            count++;
        }
        return count;
    }

    /* If the list is empty it returns null
    * If not, it returns the first element in the list*/
    @Override
    public E first() {
        if (this.isEmpty()) {
            return null;
        }
        return header.getNext().getElement();
    }

    /* Returns get the last element in the list. */
    @Override
    public E last() {
        if (this.isEmpty()) {
            return null;
        }
        return header.getPrevious().getElement();
    }

    /* Returns the element based on the index by iterating
     through the list until the index desired is met
     */
    @Override
    public E get(int index) {
        if ((index < 0) || (index >= this.size())){
            throw new IndexOutOfBoundsException();
        }
        Node<E> target = nodeFinderIterator(index);
        return target.getElement();
    }

    /* Clears all nodes until the list is empty
     */
    @Override
    public void clear() {
        while (!this.isEmpty()) {
            this.remove(0);
        }
    }

    /* Verifies if a element e is in the list by checking
    for the first index of e
    Returns true if found else false
     */
    @Override
    public boolean contains(E e) {
        if(firstIndex(e) != -1){
            return true;
        }
        return false;
    }

    /* Checks the size of the list, if its 0 its empty */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /* Returns the index (int) of the first element by
     iterating over the list.
     Returns -1 if element isn't found in the list.
     */
    @Override
    public int firstIndex(E e) {
        int index = 0;
        Node<E> temp = this.header;
        while ((index < this.size()) && !temp.getNext().getElement().equals(e) ) {
            temp = temp.getNext();
            index ++;
        }
        if ((index < size()) && temp.getNext().getElement().equals(e))
            return index;
        return -1;
    }

    /* iterates over the whole list looking for the element
     */
    @Override
    public int lastIndex(E e) {
        int index = 0;
        int lastIndex = -1;
        Node<E> temp = this.header;
        while (index < this.size()) {
            if (temp.getNext().getElement().equals(e))
                lastIndex = index;
            temp = temp.getNext();
            index ++;
        }
        return lastIndex;
    }


    /* Creates an iterator that goes forward in the list */
    private class ListIterator<E> implements Iterator<E> {

        private Node<E> currentNode;
        private int count;

        public ListIterator() {
            this.currentNode = (Node<E>) header;
            this.count = 0;
        }

        public ListIterator(int index) {
            int i = 0;
            this.currentNode = (Node<E>) header;
            while (i < index) {
                this.currentNode = this.currentNode.getNext();
                i++;
            }
            this.count = 0;
        }


        @Override
        public boolean hasNext() {
            return count < size();
        }

        @Override
        public E next() {
            if (this.hasNext()) {
                E result = this.currentNode.getNext().getElement();
                this.currentNode = this.currentNode.getNext();
                this.count ++;
                return result;
            }else {
                throw new NoSuchElementException();
            }
        }

    }

    //returns instance of iterator
    @Override
    public Iterator<E> iterator() {
        return new ListIterator<E>();
    }

    //returns instance of iterator with a parameter
    @Override
    public Iterator<E> iterator(int index) {
        return new ListIterator<E>(index);
    }


    //returns an instance of ReverseListIterator
    @Override
    public ReverseIterator<E> reverseIterator() {
        return new ReverseListIterator<E>();
    }

    //returns an instance of ReverseListIterator with parameter
    @Override
    public ReverseIterator<E> reverseIterator(int index) {
        return new ReverseListIterator<E>(index);
    }


    /*nodefinderiterator is an iterator method that goes through all the list looking for
    the specific element that is being looked for
     */
    private Node<E> nodeFinderIterator(int index) {
        Node<E> temp = this.header.getNext();
        int i = 0;
        while (i < index) {
            temp = temp.getNext();
            i++;
        }
        return temp;
    }

    //Adds the node in the middle of these 2
    private void addMiddle(Node<E> P, Node<E> N, E e) {
        Node<E> nNode = new Node<E>(N, P, e);
        P.setNext(nNode);
        N.setPrevious(nNode);
    }
    /* Reverse ListIterator */
    private class ReverseListIterator<E> implements ReverseIterator<E> {
        private Node<E> currentNode;
        private int count;

        public ReverseListIterator() {
            this.currentNode = (Node<E>) header;
            this.count = 0;
        }

        public ReverseListIterator(int index) {
            int i = 0;
            this.currentNode = (Node<E>) header;
            while (i < index) {
                this.currentNode = this.currentNode.getPrevious();
                i++;
            }
            this.count = 0;
        }

        @Override
        public boolean hasPrevious() {
            return this.count < size();
        }

        @Override
        public E previous() {
            if (this.hasPrevious()) {
                E result = this.currentNode.getPrevious().getElement();
                this.currentNode = this.currentNode.getPrevious();
                this.count ++;
                return result;
            }else {
                throw new NoSuchElementException();
            }
        }

    }

}

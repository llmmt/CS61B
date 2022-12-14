package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T> {

    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T x, Node n, Node p) {
            item = x;
            next = n;
            prev = p;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(T x) {
        size = 1;
        sentinel = new Node(x, null, null);
        sentinel.next = new Node(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
    }
    public LinkedListDeque(){
        size = 0;

        sentinel = new Node(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    @Override
    public void addFirst(T x){
    size += 1;
    Node newItem = new Node(x,sentinel.next,sentinel);
    sentinel.next.prev = newItem;
    sentinel.next = newItem;
    }

    @Override
    public void addLast(T x){
    size += 1;
    Node newItem = new Node(x,sentinel,sentinel.prev);
    sentinel.prev.next = newItem;
    sentinel.prev = newItem;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void printDeque() {
        Node p = sentinel;
        while(p.next != sentinel && p != sentinel){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println(p.item);
    }
    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        size -= 1;
        T result = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return result;
    }
    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        size -= 1;
        T result = sentinel.prev.item;
        sentinel.prev.next = null;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return result;
    }
    @Override
    public T get(int Index){
        if(Index >= size || Index < 0){
            return null;
        }
        Node p = sentinel.next;
        for(int i = 0;i != Index;i++){
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int Index){
        if(Index >= size || Index < 0){
            return null;
        } else {
            return helper_getRecursive(Index,sentinel.next);
        }

    }

    private T helper_getRecursive(int Index,Node p){
        if(Index == 0){
            return p.item;
        } else {
            return helper_getRecursive(Index-1,p.next);
        }
    }

    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T>{
        int wizpos;
        public LinkedListIterator(){
            wizpos = 0;
        }

        @Override
        public boolean hasNext(){
            return wizpos < size;
        }

        @Override
        public T next(){
            Node temp = sentinel.next;
            for(int i=0;i != wizpos;i++){
                temp = temp.next;
            }
            wizpos += 1;
            return temp.item;
        }


    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Deque)){
            return false;
        }
        LinkedListDeque temp = (LinkedListDeque) o;
        if(temp.size() != this.size()){
            return false;
        }

        for(int i = 0;i < size;i++){
            T item1 = this.get(i);
            T item2 = (T)temp.get(i);
            if(! item2.equals(item1)){
                return false;
            }
        }
        return true;
    }
}
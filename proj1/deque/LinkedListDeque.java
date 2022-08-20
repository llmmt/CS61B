package deque;

public class LinkedListDeque<T> {
    public class Node {
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

    public void addFirst(T x){
    size += 1;
    Node newItem = new Node(x,sentinel.next,sentinel);
    sentinel.next.prev = newItem;
    sentinel.next = newItem;
    }


    public void addLast(T x){
    size += 1;
    Node newItem = new Node(x,sentinel,sentinel.prev);
    sentinel.prev.next = newItem;
    sentinel.prev = newItem;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        Node p = sentinel;
        while(p.next != sentinel && p != sentinel){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println(p.item);
    }

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
}
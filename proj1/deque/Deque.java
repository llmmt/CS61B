package deque;

public interface Deque<T> {
    default boolean isEmpty(){
        return size() == 0;
    }
     void addFirst(T x);
     void addLast(T x);
     int size();
     void printDeque();
     T removeFirst();
     T removeLast();
     T get(int index);
}

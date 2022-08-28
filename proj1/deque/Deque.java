package deque;

public interface Deque<T> {
    default boolean isEmpty(){
        return size() == 0;
    }
    public void addFirst(T x);
    public void addLast(T x);
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
}

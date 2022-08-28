package deque;


public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }
    @Override
    public void addFirst(T x) {
        size += 1;
        items[nextFirst] = x;
        nextFirst -= 1;
        if(nextFirst < 0){
            nextFirst = items.length - 1;
        }
    }
    @Override
    public void addLast(T x) {
        size += 1;
        if (nextLast >= items.length) {
            nextLast = 0;
        }
        items[nextLast] = x;
        nextLast += 1;
    }


    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        } else {
            if (nextLast > nextFirst) {
                for (int i = nextFirst + 1; i < nextLast; i++) {
                    System.out.print(items[i] + " ");
                }
                System.out.println(" ");
            } else {
                for (int i = nextFirst + 1; i < items.length - 1; i++) {
                    System.out.print(items[i] + " ");
                }
                for (int i = 0; i < nextLast; i++) {
                    System.out.print(items[i] + " ");
                }
                System.out.println(" ");
            }
        }


    }
    @Override
    public T removeFirst(){
        if(size == 0){
            return null;
        } else {
            size -= 1;
            T result = null;
            if(nextFirst + 1 == items.length){
                result = items[0];
                nextFirst = 0;
            } else {
                result = items[nextFirst + 1];
                nextFirst += 1;
            }

            return result;
        }
    }
    @Override
    public T removeLast(){
        if(size == 0){
            return null;
        } else {
            size -= 1;
            T result = null;
            if(nextLast - 1 == -1){
                result = items[items.length - 1];
                nextLast = items.length - 1;
            } else {
                result = items[nextLast - 1];
                nextLast -= 1;
            }
            return result;
        }
    }
    @Override
    public T get(int index){
        if(index < 0 || index > size){
            return null;
        }
        T result = null;
        for(int i = nextFirst + 1,j = 0;j < size;j++){
            if(i >= items.length){
                i = 0;
            }
            if(j == index){
                result = items[i];
                break;
            }
            i += 1;

        }
        return result;
    }

}
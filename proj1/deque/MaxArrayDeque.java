package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    Comparator<T> myComparator;
    public MaxArrayDeque(){
    }

    public MaxArrayDeque(Comparator<T> c){
        myComparator = c;
    }

    public T max(){
        if(size == 0){
            return null;
        } else {
            int maxIndex = nextFirst + 1;
            if(nextFirst < nextLast){
                for(int i = nextFirst + 2;i < nextLast;i++){
                    if(myComparator.compare(items[i],items[maxIndex]) > 0) {
                        maxIndex = i;
                    }
                    }
                return items[maxIndex];
            } else {
                for(int i = nextFirst +2;i < items.length;i++){
                    if(myComparator.compare(items[i],items[maxIndex]) > 0) {
                        maxIndex = i;
                    }
                }

                for(int i = 0;i < nextLast;i++){
                    if(myComparator.compare(items[i],items[maxIndex]) > 0) {
                        maxIndex = i;
                    }
                }
                return items[maxIndex];
            }
        }
    }


    public T max(Comparator<T> c){
        if(size == 0){
            return null;
        } else {
            int maxIndex = nextFirst + 1;
            if(nextFirst < nextLast){
                for(int i = nextFirst + 2;i < nextLast;i++){
                    if(c.compare(items[i],items[maxIndex]) > 0) {
                        maxIndex = i;
                    }
                }
                return items[maxIndex];
            } else {
                for(int i = nextFirst +2;i < items.length;i++){
                    if(c.compare(items[i],items[maxIndex]) > 0) {
                        maxIndex = i;
                    }
                }

                for(int i = 0;i < nextLast;i++){
                    if(c.compare(items[i],items[maxIndex]) > 0) {
                        maxIndex = i;
                    }
                }
                return items[maxIndex];
            }
        }
    }
}

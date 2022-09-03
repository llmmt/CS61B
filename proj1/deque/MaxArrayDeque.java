package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> myComparator;


    public MaxArrayDeque(Comparator<T> c){
        myComparator = c;
    }

    public T max(){
        if(this.size() == 0){
            return null;
        } else {
            int maxIndex = this.getNextFirst() + 1;
            if(this.getNextFirst() < this.getNextLast()){
                for(int i = this.getNextFirst() + 2;i < this.getNextLast();i++){
                    if(myComparator.compare(this.getItems()[i],this.getItems()[maxIndex]) > 0) {
                        maxIndex = i;
                    }
                    }
                return this.getItems()[maxIndex];
            } else {
                for(int i = this.getNextFirst() +2;i < this.getItems().length;i++){
                    if(myComparator.compare(this.getItems()[i],this.getItems()[maxIndex]) > 0) {
                        maxIndex = i;
                    }
                }

                for(int i = 0;i < this.getNextLast();i++){
                    if(myComparator.compare(this.getItems()[i],this.getItems()[maxIndex]) > 0) {
                        maxIndex = i;
                    }
                }
                return this.getItems()[maxIndex];
            }
        }
    }


    public T max(Comparator<T> c){
        if(this.size() == 0){
            return null;
        } else {
            int maxIndex = this.getNextFirst() + 1;
            if(this.getNextFirst() < this.getNextLast()){
                for(int i = this.getNextFirst() + 2;i < this.getNextLast();i++){
                    if(c.compare(this.getItems()[i],this.getItems()[maxIndex]) > 0) {
                        maxIndex = i;
                    }
                }
                return this.getItems()[maxIndex];
            } else {
                for(int i = this.getNextFirst() +2;i < this.getItems().length;i++){
                    if(c.compare(this.getItems()[i],this.getItems()[maxIndex]) > 0) {
                        maxIndex = i;
                    }
                }

                for(int i = 0;i < this.getNextLast();i++){
                    if(c.compare(this.getItems()[i],this.getItems()[maxIndex]) > 0) {
                        maxIndex = i;
                    }
                }
                return this.getItems()[maxIndex];
            }
        }
    }
}

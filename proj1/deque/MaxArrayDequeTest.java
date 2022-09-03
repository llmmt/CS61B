package deque;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;

public class MaxArrayDequeTest {
    public static class stringComparator implements Comparator<String> {
        public int compare(String s1,String s2){
            return s1.compareTo(s2);
        }
    }

    public static class sizeComparator implements Comparator<String> {
        public int compare(String i1,String i2){
            Integer l1 = i1.length();
            Integer l2 = i2.length();
            return l1.compareTo(l2);
        }
    }

    @Test
    public void test1(){
        stringComparator sc = new stringComparator();
        MaxArrayDeque<String> smad = new MaxArrayDeque<>(sc);

        smad.addLast("hello");

        smad.addLast("java");

        String expected = "java";
        String result = smad.max();
        assertEquals(expected,result);
    }

    @Test
    public void test2(){
        sizeComparator sc = new sizeComparator();
        MaxArrayDeque<String> smad = new MaxArrayDeque<>(sc);

        smad.addLast("hello");
        smad.addLast(",");
        smad.addLast("java");


        int expected = 5; // length of hello
        int result = smad.max().length();
        assertEquals(expected,result);
    }

}

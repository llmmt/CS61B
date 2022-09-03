package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;


public class ArrayLinkedListDequeTest {
    /**@Test
    public void randomizedTest(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                ad.addLast(randVal);
                lld.addLast(randVal);
                int last = ad.get(ad.size() - 1);
                assertEquals(randVal,last);
                assertEquals(ad.get(ad.size() - 1),lld.get(lld.size() - 1));
            } else if (operationNumber == 1) {
                // size
                assertEquals(ad.size(),lld.size());

            } else if (operationNumber == 2 && ad.size() != 0 && lld.size() != 0) {
                //getLast
                int adLast = ad.get(ad.size() - 1);
                int lldLast = lld.get(lld.size() - 1);
                assertEquals(adLast,lldLast);


            } else if (operationNumber == 3 && ad.size() != 0 && lld.size() != 0) {
                //removeLast
                int lastL = ad.removeLast();
                int lastB = lld.removeLast();
                assertEquals(lastB,lastL);
            }
        }
    }*/


    @Test
    public void test(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for(int i = 0; i < 8;i++){
            ad.addLast(i);
        }

        for(int i =0;i < 8;i++){
            int actual = ad.get(i);
            assertEquals(i,actual);
        }
    }

    @Test
    public void ArrayDequeIteratorTest(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(0);
        ad.addLast(1);
        ad.addLast(1);
        ad.addLast(1);
        ad.addLast(1);
        ad.addLast(1);
        ad.addLast(1);
        ad.addLast(1);
        for(int i:ad){
            System.out.println(i);
        }
    }

    @Test
    public void ArrayDequeEqualsTest(){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(0);
        ad.addLast(1);

        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        ad2.addLast(0);
        ad2.addLast(1);

        boolean equal = ad.equals(ad2);

        assertTrue(equal);

        ad.addLast(2);
        ad2.addLast(3);
        ad.addLast(1);
        ad2.addLast(1);
        ad.addLast(1);
        ad2.addLast(1);

        equal = ad.equals(ad2);

        assertTrue(!equal);
    }
}

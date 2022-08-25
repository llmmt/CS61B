package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE

        int N = 1000;
        int M = 10000;
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> ops = new AList<>();
        for(int i =0;i < 7;i++){
            SLList<Integer> sl = new SLList<>();
            createNItems(N,sl);
            Ns.addLast(N);
            times.addLast(timeGetMItems(M,sl));
            ops.addLast(M);
            N *= 2;
        }
        printTimingTable(Ns,times,ops);
    }

    private static void createNItems(int N,SLList<Integer> sl){
        for(int i =0;i < N;i++){
            sl.addLast(1);
        }
    }

    private static double timeGetMItems(int M,SLList<Integer> sl){
        double totaltimes = 0;
        for(int i = 0;i < M;i++){
            Stopwatch sw = new Stopwatch();
            sl.getLast();
            double timeOne = sw.elapsedTime();
            totaltimes += timeOne;
        }
        return totaltimes;
    }
}

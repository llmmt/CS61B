package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double>  times = new AList<>();
        AList<Integer> ops = new AList<>();
        int s = 1000;
        for(int i = 0;i < 8;i++){
            helper(s,Ns,times,ops);
            s = s * 2;
        }
        printTimingTable(Ns,times,ops);
    }

    private static void helper(int size,AList<Integer> Ns, AList<Double> times, AList<Integer> ops){
        double totaltimes = 0;
        Ns.addLast(size);
        AList<Integer> test = new AList<>();
        for(int j = 0;j < size;j++){
            Stopwatch sw = new Stopwatch();
            test.addLast(1);
            double timeInSeconds = sw.elapsedTime();
            totaltimes += timeInSeconds;
        }
        times.addLast(totaltimes);
        ops.addLast(size);

    }
}

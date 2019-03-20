import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] result;
    private static final double CONFID = 1.96;
    private final double mean;
    private final double stddev;
    public PercolationStats(int n, int trials) {
        // perform trials independent experiments on an n-by-n grid
        if(n < 1) throw new IllegalArgumentException("Int " + n + " is not greater than 0");
        if(trials < 1) throw new IllegalArgumentException("Int " + trials + " is not greater than 0");
        result = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                p.open(1 + StdRandom.uniform(n), 1 + StdRandom.uniform(n));
            }
            result[i] = (double)p.numberOfOpenSites()/ (n * n);
        }
        mean = StdStats.mean(result);
        stddev = StdStats.stddev(result);
    }
    
    public double mean() {
        // sample mean of percolation threshold
        return mean;
    }
    public double stddev() {
        // sample standard deviation of percolation threshold
        return stddev;
    }
    public double confidenceLo() {
        // low  endpoint of 95% confidence interval
        return mean() - CONFID * stddev() / Math.sqrt(result.length);
    }
    public double confidenceHi() {
        // high endpoint of 95% confidence interval
        return mean() + CONFID * stddev() / Math.sqrt(result.length);
        }

    public static void main(String[] args) {
        // test client (described below)
        PercolationStats ps = new PercolationStats(2, 100000);
        System.out.printf("mean                     = %f%n", ps.mean());
        System.out.printf("stddev                   = %f%n", ps.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]", ps.confidenceLo(), ps.confidenceHi());
    }
}

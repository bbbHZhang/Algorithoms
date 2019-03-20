import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   private WeightedQuickUnionUF unionf;
   private WeightedQuickUnionUF unionfBottom;
   private boolean[] status;
   private int gridLen;
   private int counter;
   
   public Percolation(int n) {
       // create n-by-n grid, with all sites blocked
       if(n < 1) throw new IllegalArgumentException("Int " + n + " is not greater than 0");
       //the first is virtual top
       unionf = new WeightedQuickUnionUF(n * n + 1);
       //and the last is virtual bottom
       unionfBottom = new WeightedQuickUnionUF(n * n + 2);
       //create a boolean array to track the status of all sites
       //the first one is never used
       status = new boolean[n * n + 1];
//       for (int i = 0; i < status.length; i++) {
//           status[i] = false;
//       }
       gridLen = n;
       counter = 0;
       
   }
   
   private void validate(int p) {
       if (p < 1 || p > gridLen) {
           throw new IllegalArgumentException("index " + p + " is not between 1 and " + (gridLen));  
       }
   }
   
   
   private int index(int row, int col) {
//       validate(row);
//       validate(col);
       if (row >= 1 && row <= gridLen && col >= 1 && col <= gridLen) {
           return (row - 1) * gridLen + col;
       }
       else return -1;
   }
   public    void open(int row, int col) {
       // open site (row, col) if it is not open already
       validate(row);
       validate(col);
       int index = index(row, col);
       //do nothing if it is already open
       if(status[index]) return;
       
       status[index] = true;
       counter++;
       
       int tmp;
       //connect with up down left and right
       //up
       if ((tmp = index(row - 1, col)) != -1 && status[tmp]) {
           unionf.union(index, tmp);
           unionfBottom.union(index, tmp);
       }
       //down
       if ((tmp = index(row + 1, col)) != -1 && status[tmp]) {
           unionf.union(index, tmp);
           unionfBottom.union(index, tmp);
       }
       //left
       if ((tmp = index(row, col - 1)) != -1 && status[tmp]) {
           unionf.union(index, tmp);
           unionfBottom.union(index, tmp);
       }
       //right
       if ((tmp = index(row, col + 1)) != -1 && status[tmp]) {
           unionf.union(index, tmp);
           unionfBottom.union(index, tmp);
       }
       
       //the first row should be connected to the virtual top
       if (row == 1) {
           unionf.union(index, 0);
           unionfBottom.union(index, 0);
       }
       
       //the last row should be connected to the virtual bottom
       if(row == gridLen) {
           unionfBottom.union(index, gridLen * gridLen + 1);
       }
       
   }
   public boolean isOpen(int row, int col) {
       // is site (row, col) open?
       validate(row);
       validate(col);
       return status[index(row, col)];
       
   }
   public boolean isFull(int row, int col) {
       // is site (row, col) full?
       validate(row);
       validate(col);
       return unionf.connected(0, index(row, col));
   }
   public     int numberOfOpenSites() {
       // number of open sites
       return counter;
   }
   public boolean percolates() {
       // does the system percolate?
       return unionfBottom.connected(0, gridLen * gridLen + 1);
   }
   
   @Override
   public String toString() {
       StringBuilder sb = new StringBuilder();
       for(int i = 1; i < status.length; i++) {
           if((i-1) % gridLen == 0) {
               sb.append("\n");
           }
           sb.append(status[i]);
       }
       return sb.toString();
       
   }
   public static void main(String[] args) {
       Percolation p = new Percolation(3);
       for(boolean b: p.status) {
           System.out.print(b + ", ");
       }
       p.open(1, 3);
       p.open(2, 3);
       p.open(3, 3);
       System.out.println(p.percolates());
       System.out.println(p.toString());
       System.out.println(p.unionf.connected(0, p.index(1, 3)));
   }

}
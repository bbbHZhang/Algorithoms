import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
   public static void main(String[] args) {
       //leave empty on purpose
       RandomizedQueue<String> q = new RandomizedQueue<String>();
       int k = Integer.parseInt(args[0]);
       while (true) {
           try {
               String s = StdIn.readString();
               q.enqueue(s);
           } catch (NoSuchElementException e) {
               break;
           }
       }
       for(int i = 0; i < k; i++) {
           System.out.println(q.dequeue());
       }
   }
}

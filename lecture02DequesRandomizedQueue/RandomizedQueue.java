import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Object[] items;
    private static final int INITIAL_CAP = 16;
    private int size;
    
    public static void main(String[] args) {
        // unit testing (optional)
        RandomizedQueue<Integer> d = new RandomizedQueue<>();
        d.enqueue(1);
        d.enqueue(2);
        d.enqueue(3);
        d.enqueue(4);
        System.out.println(d.dequeue());
        System.out.println(d.dequeue());
        System.out.println(d.dequeue());
        System.out.println(d.dequeue());
    }
    
    public RandomizedQueue() {
        // construct an empty randomized queue
        items = new Object[INITIAL_CAP];
        size = 0;
        
    }
    public boolean isEmpty() {
        // is the randomized queue empty?
        return size == 0;
    }
    public int size() {
        // return the number of items on the randomized queue
        return size;
    }
    public void enqueue(Item item) {
        // add the item
        if(item == null) throw new IllegalArgumentException();
        if(size == items.length) {
            Object[] tmp = items;
            items = new Object[items.length * 2];
            System.arraycopy(tmp, 0, items, 0, tmp.length);
            items[size++] = item;
            return;
        }
        
        items[size++] = item;
    }
    public Item dequeue() {
        // remove and return a random item
        if(isEmpty()) throw new NoSuchElementException();
        int pointer = StdRandom.uniform(0, size);
        Item res = (Item)items[pointer];
        items[pointer] = items[size - 1];
        items[size-- - 1] = null;
        if(size < items.length / 4) {
            //shrink the items
            Object[] tmp = items;
            items = new Object[items.length / 2];
            System.arraycopy(tmp, 0, items, 0, size);
        }
        return res;
    }
    public Item sample() {
        // return a random item (but do not remove it)
        if(isEmpty()) throw new NoSuchElementException();
        int pointer = StdRandom.uniform(0, size);
        Item res = (Item)items[pointer];
        return res;
    }
    
    @Override
    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandomIterator();
    }
    
    private class RandomIterator implements Iterator<Item>{
        private final boolean[] flags = new boolean[size];
        private int s = size;

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return s != 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int tmp = StdRandom.uniform(0, size);
            while(flags[tmp]) {
                tmp = StdRandom.uniform(0, size);
            }
            Item res = (Item)items[tmp];
            s--;
            flags[tmp] = true;
            return res;
        }
        
    }

}

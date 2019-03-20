import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Object[] items;
    private static final int INITIAL_CAP = 16;
    private int size;
    private int front;
    private int back;
    
    public Deque() {
        // construct an empty deque
        items = new Object[INITIAL_CAP];
        //do i need these
        front = 0;
        back = -1;
    }
    public boolean isEmpty() {
        // is the deque empty?
        return size() == 0;
    }
    public int size() {
        // return the number of items on the deque
        return size;
    }
    
    public void addFirst(Item item) {
        if(item == null) throw new IllegalArgumentException("item should not be null");
        // add the item to the front
//        System.out.println("front: " + front + " back " + back);

//        if (isEmpty()) {
//            items[front] = item;
//            size++;
//            return;
//        }

        //if full, double the size
        if (size == items.length) {
            Object[] tmp = items;
            items = new Object[items.length * 2];
            items[0] = item;
            //copy all the items to the new array
            int index = 1;
            int i = Math.floorMod(front, tmp.length);
            while (index <= tmp.length) {
               items[index++] = tmp[i];
               i = ++i % tmp.length;
            }
            size++;
            front = 0;
            back = size - 1;
            return;
        }
        
        //if not full, move the front and add item
        front--;
        int index = Math.floorMod(front, items.length);
        items[index] = item;
        size++;

    }
    
    public void addLast(Item item) {
        // add the item to the end
        if(item == null) throw new IllegalArgumentException("item should not be null");
//        System.out.println("front: " + front + " back " + back);

        // add the item to the front
//        if (isEmpty()) {
//            items[back] = item;
//            size++;
//            return;
//        }
        
        //if full, double the size
        if (size == items.length) {
            Object[] tmp = items;
            items = new Object[items.length * 2];
            //copy all the items to the new array
            int index = 0;
            int i = Math.floorMod(front, tmp.length);
            while (index < tmp.length) {
               items[index++] = tmp[i];
               i = ++i % tmp.length;
            }
            front = 0;
            items[index] = item;
            size++;
            back = size - 1;
            return;
        }
        
        //if not full, move the front and add item
        
        items[Math.floorMod(++back, items.length)] = item;
        size++;
//        System.out.println("addlast after front: " + front + " back " + back);
    }
    public Item removeFirst() {
        
        // remove and return the item from the front
        if(isEmpty()) throw new NoSuchElementException("Deck is empty!");
        Item res = (Item) items[Math.floorMod(front, items.length)];
        items[Math.floorMod(front, items.length)] = null;
        size--;
        front++;
        //if size < 0.25 length, shrink
        if(size < 0.25 * items.length && items.length > INITIAL_CAP) {
            Object[] tmp = items;
            items = new Object[items.length / 2];
            int index = 0;
            int i = Math.floorMod(front, tmp.length);
            while (index < size) {
               items[index] = tmp[i];
               i = ++i % tmp.length;
               index++;
            }
            front = 0;
            back = size - 1;
        }
        
        return res;
    }
    public Item removeLast() {
        // remove and return the item from the end
        if(isEmpty()) throw new NoSuchElementException("Deck is empty!");
//        System.out.println("front: " + front + " back " + back);
        Item res = (Item)items[Math.floorMod(back, items.length)];
        items[Math.floorMod(back, items.length)] = null;
        size--;
        back--;
        //shrink if it is way more small
        if(size < items.length / 4 && items.length > INITIAL_CAP) {
            Object[] tmp = items;
            items = new Object[items.length / 2];
            int index = 0;
            int i = Math.floorMod(front, tmp.length);
            while (index < size) {
               items[index] = tmp[i];
               i = ++i % tmp.length;
               index++;
            }
            front = 0;
            back = size - 1;
        }
        return res;
    }

    @Override
    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item>{
        private int index = Math.floorMod(front, items.length);
        private int s = size;

        @Override
        public boolean hasNext() {
            return s != 0;
        }

        @Override
        public Item next() {
            // TODO Auto-generated method stub
            if(!hasNext()) throw new NoSuchElementException("No more item!");
            Item tmp = (Item) items[index];
            index = Math.floorMod(++index, items.length);
            s--;
            return tmp;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = size;
        int index = Math.floorMod(front, items.length);
        while(i > 0) {
            sb.append(items[index]).append(", ");
            i--;
            index = ++index % items.length;
        }
        return sb.toString();
    }
    

    public static void main(String[] args) {
        // unit testing (optional)
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        System.out.println(deque.removeFirst());
        System.out.println(deque.isEmpty());
        deque.addLast(4);
        deque.addLast(5);
        System.out.println(deque.removeFirst());
    }


}


package ua.edu.ucu.iterators;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StreamIterator implements Iterator<Integer> {
    private final int[] elements;
    private int i = 0;

    public StreamIterator(int... values) {
        this.elements = values;
    }

    @Override
    public boolean hasNext() {
        return (this.elements.length > i);
    }

    @Override
    public Integer next() {
        this.i++;
        Integer res;
        try {
            res =  this.elements[i-1];
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
        return res;
    }
}
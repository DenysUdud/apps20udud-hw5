package ua.edu.ucu.iterators;
import java.util.Iterator;

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
        return this.elements[i-1];
    }
}
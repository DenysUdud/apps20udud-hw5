package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIterator implements Iterator {
    private final IntToIntStreamFunction func;
    private final Iterator<Integer> parentIterator;
    private StreamIterator newIterator;
    private int index;

    public FlatMapIterator(Iterator<Integer> parentIterator, IntToIntStreamFunction func) {
        this.func = func;
        this.parentIterator = parentIterator;
        this.newIterator = new StreamIterator();
    }

    @Override
    public boolean hasNext() {
        if (newIterator.hasNext()) {
            return true;
        } else if (parentIterator.hasNext()) {
            AsIntStream tempStream = (AsIntStream) func.
                    applyAsIntStream(parentIterator.next());
            newIterator = new StreamIterator(tempStream.toArray());
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        return newIterator.next();
    }
}

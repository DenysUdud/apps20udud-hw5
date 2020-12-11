package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;


public class MapIterator implements Iterator {
    private final Iterator<Integer> parentIterator;
    private final IntUnaryOperator mapper;
    private Integer next;

    public MapIterator(Iterator<Integer> parentIterator, IntUnaryOperator mapper) {
        this.parentIterator = parentIterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return parentIterator.hasNext();
    }

    @Override
    public Integer next() {
        if (parentIterator.hasNext()) {
            return mapper.apply(parentIterator.next());
        }
        return null;
    }
}

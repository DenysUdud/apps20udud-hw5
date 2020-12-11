package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;
import java.util.OptionalInt;

public class FilterIterator implements Iterator {
    private final Iterator<Integer> parentIterator;
    private final IntPredicate predicate;
    private Integer next;

    public FilterIterator(Iterator<Integer> parentIterator, IntPredicate predicate) {
        this.parentIterator = parentIterator;
        this.predicate = predicate;
        generateNext();
        }

    @Override
    public boolean hasNext() {
        return (next != null);
    }

    @Override
    public Integer next() {
        Integer currNext = this.next;
        generateNext();
        return currNext;
    }

    private void generateNext() {
        /*
        A method used to generate next in iterator using predicate.
        If there won't be any next, this.next will be null.
         */
        Integer currentNext;

        while (parentIterator.hasNext()) {
            currentNext = parentIterator.next();
            if (predicate.test(currentNext)) {
                this.next = currentNext;
                return;
            }
        }
        this.next = null;
    }
}

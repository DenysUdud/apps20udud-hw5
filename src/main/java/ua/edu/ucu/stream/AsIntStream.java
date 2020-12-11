package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.FlatMapIterator;
import ua.edu.ucu.iterators.MapIterator;
import ua.edu.ucu.iterators.StreamIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class AsIntStream implements IntStream {
    private final Iterator<Integer> iterator;

    private AsIntStream(int... values) {
        this.iterator = new StreamIterator(values);
    }

    private AsIntStream(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
       isEmpty();

       int numValues = 0;
       Double sum = 0.0;

       while (iterator.hasNext()) {
           sum += iterator.next();
           numValues++;
       }
       return sum / numValues;
    }

    @Override
    public Integer max() {
        return getMaxMin(true);
    }

    @Override
    public Integer min() {
        return getMaxMin(false);
    }

    public Integer getMaxMin(boolean max) {
        /*
        The method used to find min or maximum value.
        to get max - max = true, to get min, max = false.
         */

        Integer currValue;
        Integer value = iterator.next();

        while (iterator.hasNext()) {
            currValue = iterator.next();
            if (value < currValue == max) {
                value = currValue;
            }
        }
        return value;
    }

    @Override
    public long count() {
        if (!iterator.hasNext()) { return 0; }
        return reduce(0, (num, x) -> num++);
    }

    @Override
    public Integer sum() {
        return reduce(0, (sum, x) -> sum += x);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(this.iterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(this.iterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(iterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        isEmpty();
        while (iterator.hasNext()) {
            identity = op.apply(identity, iterator.next());
        }
        return identity;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        int size = 0;

        while (iterator.hasNext()) {
            size++;
            arrayList.add(iterator.next());
        }

        int[] streamArray = new int[size];
        for (int i = 0; i < size; i++) {
            streamArray[i] = arrayList.get(i);
        }

        return Arrays.copyOf(streamArray, size);
    }

    private void isEmpty() {
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Stream is empty.");
        }
    }

}

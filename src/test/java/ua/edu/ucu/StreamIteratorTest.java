package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import java.util.Arrays;

public class StreamIteratorTest {
    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testStreamFilter() {
        int[] expResult = {1, 2, 3};
        assertArrayEquals(expResult, intStream.filter(x -> x > 0).toArray());
    }

    @Test
    public void testStreamMap() {
        int[] expResult = {1, 0, 1, 4, 9};
        assertArrayEquals(expResult, intStream.map(x -> x * x).toArray());
    }

    @Test
    public void testStreamReduce() {
        int expResult = 5;
        assertEquals(expResult, intStream.reduce(0, (sum, x) -> sum += x));
    }

    @Test
    public void testStreamFlatMap() {
        int[] expResult = {0, 1, 2, 3, 4, 5, 8, 9, 10};
        assertArrayEquals(expResult, intStream.filter(x -> x > 0)
                .map(x -> x * x)
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1)).toArray()
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void isEmptyTest() {
        // from code, in average isEmpty is used. So that,
        // we can check it
        intStream.filter(x -> x < -5).average();
    }

    @Test
    public void testStreamForEach() {
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);
    }

}

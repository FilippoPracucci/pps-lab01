package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {

    private static final int STANDARD_RANGE_MAX = 3;
    private MinMaxStack minMaxStack;

    @BeforeEach
    public void setUp() {
        minMaxStack = new MinMaxStackImpl();
    }

    @Test
    public void testInitialState() {
        assertAll(
            () -> assertTrue(this.minMaxStack.isEmpty()),
            () -> assertEquals(0, this.minMaxStack.size())
        );
    }

    @Test
    public void testOrderAfterSomePushAndCheckSize() {
        final List<Integer> pushedList = pushFirstNumbersFromOneToRangeMax(STANDARD_RANGE_MAX);
        assertAll(
            () -> assertEquals(pushedList.getLast(), this.minMaxStack.peek()),
            () -> assertEquals(pushedList.size(), this.minMaxStack.size())
        );
    }

    @Test
    public void testCorrectPopsOrder() {
        final List<Integer> pushedList = pushFirstNumbersFromOneToRangeMax(STANDARD_RANGE_MAX);
        assertAll(
            () -> assertEquals(pushedList.removeLast(), this.minMaxStack.pop()),
            () -> assertEquals(pushedList.removeLast(), this.minMaxStack.pop()),
            () -> assertEquals(pushedList.removeLast(), this.minMaxStack.pop()),
            () -> assertTrue(this.minMaxStack.isEmpty())
        );
    }

    private List<Integer> pushFirstNumbersFromOneToRangeMax(final int rangeMax) {
        final List<Integer> valuesToPush = new ArrayList<>(
                IntStream.rangeClosed(1, rangeMax).boxed().toList()
        );

        for (final int value: valuesToPush) {
            this.minMaxStack.push(value);
        }

        return valuesToPush;
    }

    @Test
    public void testGetMinAndMaxIfNotEmpty() {
        final List<Integer> valuesList = new ArrayList<>(Arrays.asList(3, 5, 2, 4));
        for (final Integer value: valuesList) {
            this.minMaxStack.push(value);
        }
        assertAll(
            () -> assertEquals(Collections.min(valuesList), this.minMaxStack.getMin()),
            () -> assertEquals(Collections.max(valuesList), this.minMaxStack.getMax())
        );
    }

    @Test
    public void testGetMinAndMaxIfEmpty() {
        assertAll(
            () -> assertThrows(IllegalStateException.class, () -> this.minMaxStack.getMin()),
            () -> assertThrows(IllegalStateException.class, () -> this.minMaxStack.getMax())
        );
    }

    @Test
    public void testPopAndPeekIfEmpty() {
        assertAll(
            () -> assertThrows(IllegalStateException.class, () -> this.minMaxStack.pop()),
            () -> assertThrows(IllegalStateException.class, () -> this.minMaxStack.peek())
        );
    }
}
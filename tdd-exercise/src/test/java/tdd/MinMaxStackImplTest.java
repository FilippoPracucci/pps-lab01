package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        assertTrue(this.minMaxStack.isEmpty());
    }

    @Test
    public void testOrderAfterSomePushAndCheckSize() {
        final List<Integer> pushedList = pushFirstNumbersTillMax(STANDARD_RANGE_MAX);
        assertAll(
            () -> assertEquals(this.minMaxStack.peek(), pushedList.getLast()),
            () -> assertEquals(this.minMaxStack.size(), pushedList.size())
        );
    }

    @Test
    public void testCorrectPopsOrder() {
        final List<Integer> pushedList = pushFirstNumbersTillMax(STANDARD_RANGE_MAX);
        assertAll(
            () -> assertEquals(this.minMaxStack.pop(), pushedList.get(2)),
            () -> assertEquals(this.minMaxStack.pop(), pushedList.get(1)),
            () -> assertEquals(this.minMaxStack.pop(), pushedList.get(0)),
            () -> assertTrue(this.minMaxStack.isEmpty())
        );
    }

    private List<Integer> pushFirstNumbersTillMax(final int rangeMax) {
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
            () -> assertEquals(
                    this.minMaxStack.getMin(),
                    valuesList.stream().min(Integer::compare).orElseThrow()),
            () -> assertEquals(
                    this.minMaxStack.getMax(),
                    valuesList.stream().max(Integer::compare).orElseThrow())
        );
    }

    @Test
    public void testGetMinAndMaxIfEmpty() {
        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> this.minMaxStack.getMin()),
                () -> assertThrows(IllegalStateException.class, () -> this.minMaxStack.getMax())
        );
    }
}
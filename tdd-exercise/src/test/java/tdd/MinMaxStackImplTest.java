package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {

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
        final int numberOfPush = 4;
        final List<Integer> pushedList = pushFirstNumbersTillMax(numberOfPush);
        assertAll(
            () -> assertEquals(this.minMaxStack.peek(), pushedList.getLast()),
            () -> assertEquals(this.minMaxStack.size(), numberOfPush)
        );
    }

    @Test
    public void testCorrectPopsOrder() {
        final int rangeMax = 3;
        final List<Integer> valuesToPush = pushFirstNumbersTillMax(rangeMax);
        assertAll(
            () -> assertEquals(this.minMaxStack.pop(), valuesToPush.get(2)),
            () -> assertEquals(this.minMaxStack.pop(), valuesToPush.get(1)),
            () -> assertEquals(this.minMaxStack.pop(), valuesToPush.get(0)),
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
}
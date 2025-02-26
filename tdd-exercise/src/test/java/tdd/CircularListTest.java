package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private static final int CAPACITY = 3;
    private static final int STANDARD_MAX_RANGE = 3;
    private static final int STANDARD_MIN_RANGE = 1;
    private static final int STANDARD_NUMBER_TO_ENQUEUE = 10;
    private CircularQueue circularQueue;

    @BeforeEach
    public void setUp() {
        this.circularQueue = new CircularQueueImpl(CAPACITY);
    }

    @Test
    public void testInitialSetUp() {
        assertAll(
            () -> assertEquals(CAPACITY, this.circularQueue.capacity()),
            () -> assertFalse(this.circularQueue.isFull())
        );
    }

    @Test
    public void testFillCircularQueueAndPeek() {
        fillCircularQueue();
        assertAll(
            () -> assertTrue(this.circularQueue.isFull()),
            () -> assertEquals(STANDARD_MIN_RANGE, this.circularQueue.peek())
        );
    }

    private void fillCircularQueue() {
        for (int i = STANDARD_MIN_RANGE; i <= STANDARD_MAX_RANGE; i++) {
            this.circularQueue.enqueue(i);
        }
    }

    @Test
    public void testEnqueueIfFull() {
        fillCircularQueue();
        this.circularQueue.enqueue(STANDARD_NUMBER_TO_ENQUEUE);
        assertAll(
            () -> assertTrue(this.circularQueue.isFull()),
            () -> assertEquals(CAPACITY, this.circularQueue.size())
        );
    }

    @Test
    public void testDequeueAndPeekIfEmpty() {
        assertAll(
            () -> assertThrows(IllegalStateException.class, () -> this.circularQueue.dequeue()),
            () -> assertThrows(IllegalStateException.class, () -> this.circularQueue.peek())
        );
    }

    @Test
    public void testRightOrderAfterEnqueueIfFull() {
        final List<Integer> orderedValuesList = rightOrderedValuesList(STANDARD_NUMBER_TO_ENQUEUE);
        fillCircularQueue();
        this.circularQueue.enqueue(STANDARD_NUMBER_TO_ENQUEUE);
        assertAll(
            () -> assertTrue(this.circularQueue.isFull()),
            () -> assertEquals(orderedValuesList.removeFirst(), this.circularQueue.dequeue()),
            () -> assertEquals(orderedValuesList.removeFirst(), this.circularQueue.dequeue()),
            () -> assertEquals(orderedValuesList.removeFirst(), this.circularQueue.dequeue()),
            () -> assertTrue(this.circularQueue.isEmpty())
        );
    }

    private List<Integer> rightOrderedValuesList(final int replacingValue) {
        final List<Integer> valuesList = new ArrayList<>();
        for (int i = STANDARD_MIN_RANGE; i <= STANDARD_MAX_RANGE; i++) {
            valuesList.add(i);
        }
        valuesList.removeFirst();
        valuesList.add(replacingValue);
        return valuesList;
    }
}

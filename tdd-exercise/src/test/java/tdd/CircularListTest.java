package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private static final int CAPACITY = 3;
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
            () -> assertEquals(CAPACITY, this.circularQueue.peek())
        );
    }

    private void fillCircularQueue() {
        for (int i = CAPACITY; i > 0; i--) {
            this.circularQueue.add(i);
        }
    }

    @Test
    public void testAddToFullCircularQueue() {
        final int numberToAdd = 10;
        fillCircularQueue();
        this.circularQueue.add(numberToAdd);
        assertAll(
                () -> assertTrue(this.circularQueue.isFull()),
                () -> assertEquals(CAPACITY, this.circularQueue.size())
        );
    }
}

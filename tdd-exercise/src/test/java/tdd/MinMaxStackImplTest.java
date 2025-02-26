package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        for (int i = 0; i < numberOfPush; i++) {
            this.minMaxStack.push(i);
        }
        assertAll(
            () -> assertEquals(this.minMaxStack.peek(), numberOfPush - 1),
            () -> assertEquals(this.minMaxStack.size(), numberOfPush)
        );
    }
}
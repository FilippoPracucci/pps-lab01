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
}
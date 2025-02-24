package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private SmartDoorLock smartDoorLock;

    @BeforeEach
    void beforeEach() {
         smartDoorLock = new SmartDoorLockImpl(0000);
    }

    @Test
    public void testInitialState() {
        assertAll(
                () -> assertFalse(this.smartDoorLock.isLocked()),
                () -> assertEquals(this.smartDoorLock.getFailedAttempts(), 0)
        );
    }
}

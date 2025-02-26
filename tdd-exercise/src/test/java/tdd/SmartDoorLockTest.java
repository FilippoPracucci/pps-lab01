package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    public static final int RIGHT_PIN = 0000;
    public static final int WRONG_PIN = 999;
    private SmartDoorLock smartDoorLock;

    @BeforeEach
    void beforeEach() {
         smartDoorLock = new SmartDoorLockImpl(RIGHT_PIN);
    }

    @Test
    void testInitialState() {
        assertAll(
            () -> assertFalse(this.smartDoorLock.isLocked()),
            () -> assertEquals(this.smartDoorLock.getFailedAttempts(), RIGHT_PIN)
        );
    }

    private void multipleWrongUnlockAfterLockingDoor(final int numberOfWrongAttempts) {
        this.smartDoorLock.lock();
        for (int i = 0; i < numberOfWrongAttempts; i++) {
            this.smartDoorLock.unlock(WRONG_PIN);
        }
    }

    @Test
    void testUnlockDoorAfterTwoFailedAttempts() {
        final int numberOfWrongAttempts = 2;
        multipleWrongUnlockAfterLockingDoor(numberOfWrongAttempts);
        this.smartDoorLock.unlock(RIGHT_PIN);
        assertAll(
            () -> assertFalse(this.smartDoorLock.isLocked()),
            () -> assertFalse(this.smartDoorLock.isBlocked())
        );
    }

    @Test
    void testBlockDoorAfterTooManyAttempts() {
        final int numberOfWrongAttempts = this.smartDoorLock.getMaxAttempts() + 1;
        multipleWrongUnlockAfterLockingDoor(numberOfWrongAttempts);
        assertAll(
            () -> assertTrue(this.smartDoorLock.getFailedAttempts() >= this.smartDoorLock.getMaxAttempts()),
            () -> assertTrue(this.smartDoorLock.isBlocked()),
            () -> assertTrue(this.smartDoorLock.isLocked())
        );
    }
}

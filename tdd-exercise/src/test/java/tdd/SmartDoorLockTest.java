package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    public static final int PIN = 0000;
    private SmartDoorLock smartDoorLock;

    @BeforeEach
    void beforeEach() {
         smartDoorLock = new SmartDoorLockImpl(PIN);
    }

    @Test
    void testInitialState() {
        assertAll(
            () -> assertFalse(this.smartDoorLock.isLocked()),
            () -> assertEquals(this.smartDoorLock.getFailedAttempts(), PIN)
        );
    }

    @Test
    void testUnlockDoorAfterTwoFailedAttempts() {
        final int numberOfWrongAttempts = 2;
        final int wrongPin = 999;
        this.smartDoorLock.lock();
        for (int i = 0; i < numberOfWrongAttempts; i++) {
            this.smartDoorLock.unlock(wrongPin);
        }
        this.smartDoorLock.unlock(PIN);
        assertAll(
            () -> assertFalse(this.smartDoorLock.isLocked()),
            () -> assertFalse(this.smartDoorLock.isBlocked())
        );
    }
}

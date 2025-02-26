package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    public static final int STANDARD_RIGHT_PIN = 0000;
    public static final int WRONG_PIN = 9999;
    private SmartDoorLock smartDoorLock;

    @BeforeEach
    public void setUp() {
         smartDoorLock = new SmartDoorLockImpl();
    }

    @Test
    public void testInitialState() {
        assertAll(
            () -> assertFalse(this.smartDoorLock.isLocked()),
            () -> assertEquals(0, this.smartDoorLock.getFailedAttempts())
        );
    }

    private void multipleWrongUnlockAfterLockingDoor(final int numberOfWrongAttempts) {
        this.smartDoorLock.lock();
        for (int i = 0; i < numberOfWrongAttempts; i++) {
            this.smartDoorLock.unlock(WRONG_PIN);
        }
    }

    @Test
    public void testUnlockDoorAfterTwoFailedAttempts() {
        final int numberOfWrongAttempts = 2;
        multipleWrongUnlockAfterLockingDoor(numberOfWrongAttempts);
        this.smartDoorLock.unlock(STANDARD_RIGHT_PIN);
        assertAll(
            () -> assertFalse(this.smartDoorLock.isLocked()),
            () -> assertFalse(this.smartDoorLock.isBlocked())
        );
    }

    private void blockDoor() {
        final int numberOfWrongAttempts = this.smartDoorLock.getMaxAttempts() + 1;
        multipleWrongUnlockAfterLockingDoor(numberOfWrongAttempts);
    }

    @Test
    public void testBlockDoorAfterTooManyAttempts() {
        blockDoor();
        assertAll(
            () -> assertTrue(this.smartDoorLock.getFailedAttempts() >= this.smartDoorLock.getMaxAttempts()),
            () -> assertTrue(this.smartDoorLock.isBlocked()),
            () -> assertTrue(this.smartDoorLock.isLocked())
        );
    }

    @Test
    public void testUnlockBlockedDoorAfterReset() {
        blockDoor();
        this.smartDoorLock.reset();
        this.smartDoorLock.unlock(STANDARD_RIGHT_PIN);
        assertAll(
            () -> assertFalse(this.smartDoorLock.isBlocked()),
            () -> assertFalse(this.smartDoorLock.isLocked()),
            () -> assertEquals(0, this.smartDoorLock.getFailedAttempts())
        );
    }

    @Test
    public void testCannotSetNewPinIfBlockedDoor() {
        final int newPin = 1234;
        blockDoor();
        assertAll(
            () -> assertThrows(IllegalStateException.class, () -> this.smartDoorLock.setPin(newPin)),
            () -> assertTrue(this.smartDoorLock.isBlocked())
        );
    }

    @Test
    public void testCannotSetNewPinIfInvalid() {
        final int newIllegalPin = 123;
        assertThrows(IllegalArgumentException.class, () -> this.smartDoorLock.setPin(newIllegalPin));
    }
}

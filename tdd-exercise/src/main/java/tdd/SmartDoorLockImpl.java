package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {

    private final static int MAX_ATTEMPTS = 4;
    private final static int STANDARD_PIN = 0000;
    private final static int PIN_NUMBER_OF_DIGITS = 4;
    private int pin;
    private boolean locked;
    private int failedAttempts;
    private boolean blocked;

    public SmartDoorLockImpl() {
        this.pin = STANDARD_PIN;
        this.locked = false;
        this.failedAttempts = 0;
        this.blocked = false;
    }

    private boolean isPinValid(int pin) {
        return String.valueOf(pin).length() == PIN_NUMBER_OF_DIGITS;
    }

    @Override
    public void setPin(final int pin) {
        if (!this.blocked && !this.locked) {
            if (isPinValid(pin)) {
                this.pin = pin;
            } else {
                throw new IllegalArgumentException("The pin must be of 4 digits!");
            }
        } else {
            throw new IllegalStateException("It cannot be set a new pin if the door is locked or blocked!");
        }
    }

    private boolean isPinCorrect(final int pin) {
        return this.pin == pin;
    }

    private void increaseFailedAttempts() {
        this.failedAttempts++;
    }

    private boolean isLimitOfAttemptsReached() {
        return this.failedAttempts >= MAX_ATTEMPTS;
    }

    @Override
    public void unlock(int pin) {
        if (!this.blocked) {
            if (isPinCorrect(pin)) {
                this.locked = false;
                this.failedAttempts = 0;
            } else {
                increaseFailedAttempts();
                if (isLimitOfAttemptsReached()) {
                    this.blocked = true;
                }
            }
        }
    }

    @Override
    public void lock() {
        this.locked = true;
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public boolean isBlocked() {
        return this.blocked;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return this.failedAttempts;
    }

    @Override
    public void reset() {
        this.locked = false;
        this.blocked = false;
        this.failedAttempts = 0;
        this.pin = STANDARD_PIN;
    }
}

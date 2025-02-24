package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {

    private int pin;
    private boolean locked;
    private int failedAttempts;

    public SmartDoorLockImpl(final int pin) {
        this.pin = pin;
        this.locked = false;
        this.failedAttempts = 0;
    }

    @Override
    public void setPin(int pin) {

    }

    @Override
    public void unlock(int pin) {

    }

    @Override
    public void lock() {

    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return this.failedAttempts;
    }

    @Override
    public void reset() {

    }
}

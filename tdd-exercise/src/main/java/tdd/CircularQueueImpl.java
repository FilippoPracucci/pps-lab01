package tdd;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {

    private static final String CIRCULAR_QUEUE_EMPTY_ERROR_MESSAGE = "The CircularQueue is empty!";
    private final List<Integer> circularList;
    private final int capacity;

    public CircularQueueImpl(final int capacity) {
        this.circularList = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public boolean isFull() {
        return this.circularList.size() >= this.capacity;
    }

    @Override
    public boolean isEmpty() {
        return this.circularList.isEmpty();
    }

    @Override
    public void enqueue(final int value) {
        if (isFull()) {
            this.circularList.removeFirst();
        }
        this.circularList.add(value);
    }

    @Override
    public int peek() {
        throwExceptionIfEmptyCircularQueue();
        return this.circularList.getFirst();
    }

    @Override
    public int dequeue() {
        throwExceptionIfEmptyCircularQueue();
        return this.circularList.removeFirst();
    }

    private void throwExceptionIfEmptyCircularQueue() {
        if (isEmpty()) {
            throw new IllegalStateException(CIRCULAR_QUEUE_EMPTY_ERROR_MESSAGE);
        }
    }

    @Override
    public int size() {
        return this.circularList.size();
    }
}

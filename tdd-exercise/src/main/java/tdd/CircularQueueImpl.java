package tdd;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {

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
}

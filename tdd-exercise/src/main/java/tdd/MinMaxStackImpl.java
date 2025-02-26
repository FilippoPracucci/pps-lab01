package tdd;

import java.util.ArrayList;
import java.util.List;

public class MinMaxStackImpl implements MinMaxStack {

    private static final String STANDARD_EMPTY_LIST_EXCEPTION_MESSAGE = "The list is empty!";
    private final List<Integer> valuesList;

    public MinMaxStackImpl() {
        this.valuesList = new ArrayList<>();
    }

    @Override
    public void push(final int value) {
        this.valuesList.add(value);
    }

    @Override
    public int pop() {
        return this.valuesList.removeLast();
    }

    @Override
    public int peek() {
        return this.valuesList.getLast();
    }

    @Override
    public int getMin() {
        if (this.valuesList.isEmpty()) {
            throw new IllegalStateException(STANDARD_EMPTY_LIST_EXCEPTION_MESSAGE);
        } else {
            return this.valuesList.stream().min(Integer::compare).get();
        }
    }

    @Override
    public int getMax() {
        if (this.valuesList.isEmpty()) {
            throw new IllegalStateException(STANDARD_EMPTY_LIST_EXCEPTION_MESSAGE);
        } else {
            return this.valuesList.stream().max(Integer::compare).get();
        }
    }

    @Override
    public boolean isEmpty() {
        return this.valuesList.isEmpty();
    }

    @Override
    public int size() {
        return this.valuesList.size();
    }
}

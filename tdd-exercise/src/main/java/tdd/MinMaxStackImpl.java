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
        throwIllegalStateExceptionIfEmpty();
        return this.valuesList.removeLast();
    }

    @Override
    public int peek() {
        throwIllegalStateExceptionIfEmpty();
        return this.valuesList.getLast();
    }

    @Override
    public int getMin() {
        throwIllegalStateExceptionIfEmpty();
        return this.valuesList.stream().min(Integer::compare).orElseThrow();
    }

    @Override
    public int getMax() {
        throwIllegalStateExceptionIfEmpty();
        return this.valuesList.stream().max(Integer::compare).orElseThrow();
    }

    private void throwIllegalStateExceptionIfEmpty() {
        if (this.valuesList.isEmpty()) {
            throw new IllegalStateException(STANDARD_EMPTY_LIST_EXCEPTION_MESSAGE);
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

package tdd;

import java.util.ArrayList;
import java.util.List;

public class MinMaxStackImpl implements MinMaxStack {

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
        return 0;
    }

    @Override
    public int getMax() {
        return 0;
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

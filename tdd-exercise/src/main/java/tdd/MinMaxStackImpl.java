package tdd;

import java.util.ArrayList;
import java.util.List;

public class MinMaxStackImpl implements MinMaxStack {

    private List<Integer> valuesList;

    public MinMaxStackImpl() {
        this.valuesList = new ArrayList<>();
    }

    @Override
    public void push(int value) {

    }

    @Override
    public int pop() {
        return 0;
    }

    @Override
    public int peek() {
        return 0;
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
        return 0;
    }
}

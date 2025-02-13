package model;

import java.util.ArrayList;
import java.util.List;

public class CustomLottoNumberPicker extends LottoNumberPicker {
    private static final int INIT_INDEX = 0;
    private List<Integer> values = new ArrayList<>();
    private Integer index = INIT_INDEX;

    @Override
    public Number pickRandomNumber() {
        if (index >= values.size()) {
            index = INIT_INDEX;
        }
        return new Number(values.get(index++));
    }

    public void addValue(int value) {
        this.values.add(value);
    }
}

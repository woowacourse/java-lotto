package model;

import java.util.ArrayList;
import java.util.List;

public class CustomLottoNumberPicker extends LottoNumberPicker {
    private List<Integer> values = new ArrayList<>();
    private Integer index = 0;

    @Override
    public Number pickRandomNumber() {
        if (index >= values.size()) {
            index = 0;
        }
        return new Number(values.get(index++));
    }

    public void addValue(int value) {
        this.values.add(value);
    }
}

package model;

import java.util.Set;
import java.util.TreeSet;

public class LottoGenerater {
    private final LottoNumberPicker lottoNumberPicker;

    public LottoGenerater(LottoNumberPicker lottoNumberPicker) {
        this.lottoNumberPicker = lottoNumberPicker;
    }

    public Lotto generateLotto() {
        Set<Number> numbers = new TreeSet<>();
        while(numbers.size() != 6) {
            numbers.add(lottoNumberPicker.pickRandomNumber());
        }
        return new Lotto(numbers);
    }
}

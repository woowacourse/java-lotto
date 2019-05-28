package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLottoCreator implements LottoCreator {
    @Override
    public Lotto create() {
        List<Number> numbers = new ArrayList<>();

        for (int i = 0; i < 46; i++) {
            numbers.add(new Number(i));
        }
        Collections.shuffle(numbers);

        return new Lotto(numbers.subList(0, 6));
    }
}

package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLottoCreator implements LottoCreator {

    @Override
    public Lotto create() {
        List<Number> numbers = new ArrayList<>();

        for (int i = Number.MIN_NUMBER; i <= Number.MAX_NUMBER; i++) {
            numbers.add(new Number(i));
        }
        Collections.shuffle(numbers);
        Collections.sort(numbers.subList(0, 6));
        return new Lotto(numbers.subList(0, 6));
    }
}

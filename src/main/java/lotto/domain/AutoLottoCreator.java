package lotto.domain;

import java.util.Collections;
import java.util.List;

public class AutoLottoCreator implements LottoCreator {

    private final List<Number> numbers;

    public AutoLottoCreator(List<Number> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Lotto create() {
        Collections.shuffle(numbers);
        Collections.sort(numbers.subList(0, 6));

        return new Lotto(numbers.subList(0, 6));
    }

}

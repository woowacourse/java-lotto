package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLottoCreator implements LottoCreator {

    @Override
    public Lotto create() {
        List<Number> lotto = new ArrayList<>();
        List<Integer> numbers = shuffleNumber();

        for (Integer integer : numbers.subList(0, 6)) {
            lotto.add(Number.valueOf(integer));
        }

        return new Lotto(lotto);
    }

    private List<Integer> shuffleNumber() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = Number.MIN_NUMBER; i <= Number.MAX_NUMBER; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);
        Collections.sort(numbers.subList(0, 6));
        return numbers;
    }
}

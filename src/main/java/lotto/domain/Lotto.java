package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_COUNT = 6;

    private final List<Ball> lotto = new ArrayList<>();

    public Lotto(final List<String> numbers) {
        checkValidNumbers(numbers);

        for (String number : numbers) {
            this.lotto.add(new Ball(number));
        }
    }

    private void checkValidNumbers(final List<String> numbers) {
        checkDuplicatedNumber(numbers);
        checkLottoCount(numbers);
    }

    private void checkLottoCount(List<String> numbers) {
        if (numbers.size() > LOTTO_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private void checkDuplicatedNumber(List<String> numbers) {
        Set<String> distinctNumbers = new HashSet<>(numbers);
        if (distinctNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException();
        }
    }
}

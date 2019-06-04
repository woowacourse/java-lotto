package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMaker {
    private static final int START = 0;
    private static final int LOTTO_SIZE = 6;
    private static final int FIRST_NUMBER = 1;
    private static final int LAST_NUMBER = 45;
    private static final String INVALID_LOTTO_NUMBER_ERROR = FIRST_NUMBER + " ~ " + LAST_NUMBER
            + "사이의 값만 입력할 수 있습니다.";

    private static final List<Integer> numbers = new ArrayList<>();

    static {
        for (int i = FIRST_NUMBER; i <= LAST_NUMBER; i++) {
            numbers.add(i);
        }
    }

    public static Lotto generator(List<Integer> inputNumbers) {
        if (!numbers.containsAll(inputNumbers)) {
            throw new InvalidLotto(INVALID_LOTTO_NUMBER_ERROR);
        }
        sortAscending(inputNumbers);
        return Lotto.of(inputNumbers);
    }

    public static Lotto generator() {
        List<Integer> autoNumbers = new ArrayList<>(numbers);
        mixNumbers(autoNumbers);
        autoNumbers = autoNumbers.subList(START, LOTTO_SIZE);
        sortAscending(autoNumbers);
        return Lotto.of(autoNumbers);
    }

    private static void mixNumbers(List<Integer> numbers) {
        Collections.shuffle(numbers);
    }

    private static void sortAscending(List<Integer> numbers) {
        Collections.sort(numbers);
    }
}

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
            throw new InvalidLottoException(INVALID_LOTTO_NUMBER_ERROR);
        }
        Collections.sort(numbers);
        return Lotto.of(inputNumbers);
    }

    public static Lotto generator() {
        List<Integer> autoNumbers = new ArrayList<>(numbers);
        Collections.shuffle(numbers);
        autoNumbers = autoNumbers.subList(START, LOTTO_SIZE);
        Collections.sort(autoNumbers);
        return Lotto.of(autoNumbers);
    }
}

package lottogame.domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoNumber {
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static final int LOTTO_VOLUME = 6;
    private final List<Integer> numbers = new ArrayList<>();

    public LottoNumber() {
        for (int i = 0; i < LOTTO_VOLUME; i++) {
            numbers.add(makeNumber());
        }
    }

    private Integer makeNumber() {
        int number = LottoNumberGenerator.generate(LOTTO_MIN, LOTTO_MAX);
        if (numbers.contains(number)) {
            return makeNumber();
        }
        return number;
    }
}

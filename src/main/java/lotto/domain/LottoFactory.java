package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    private static final int LOTTO_SIZE = 6;

    public static List<LottoNumber> create() {
        List<LottoNumber> numbers = new ArrayList<>();
        while (numbers.size() < LOTTO_SIZE) {
            numbers.add(LottoNumber.getShuffledNumber());
        }
        return numbers;
    }
}

package lotto.domain;

import java.util.*;

import static lotto.domain.LottoRule.*;

public class RandomNumberGenerator {
    private static final int START = 0;

    private static List<LottoNumber> allLottoNumbers = new ArrayList<>();

    static {
        for (int i = MIN_LOTTO_NUMBER.get(); i <= MAX_LOTTO_NUMBER.get(); i++) {
            allLottoNumbers.add(LottoNumber.getInstance(i));
        }
    }

    public static List<LottoNumber> generate() {
        Collections.shuffle(allLottoNumbers);
        return allLottoNumbers.subList(START, LOTTO_SIZE.get());
    }
}

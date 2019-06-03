package lotto.domain.generator;

import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;

import java.util.*;

public class AutoLottoNumbersGenerator implements LottoNumbersGenerator {
    private static final AutoLottoNumbersGenerator INSTANCE = new AutoLottoNumbersGenerator();
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int SUBLIST_LOWER_BOUND = 0;
    private static final int SUBLIST_UPPER_BOUND = 6;
    private static final List<LottoNumber> totalLottoNumbers = new ArrayList<>();

    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            totalLottoNumbers.add(LottoNumber.valueOf(i));
        }
    }

    private AutoLottoNumbersGenerator() {
    }

    public static AutoLottoNumbersGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public LottoNumbers generate() {
        Collections.shuffle(totalLottoNumbers, new Random());
        return new LottoNumbers(new ArrayList<>(
                totalLottoNumbers.subList(SUBLIST_LOWER_BOUND, SUBLIST_UPPER_BOUND)));
    }
}

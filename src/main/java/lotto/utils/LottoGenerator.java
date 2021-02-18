package lotto.utils;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator implements RandomGenerator {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int FROM_INDEX = 0;

    private List<LottoNumber> numbers = new ArrayList<>();

    public LottoGenerator() {
        createNumber();
    }

    private void createNumber() {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            numbers.add(new LottoNumber(Integer.toString(i)));
        }
    }

    @Override
    public Lotto generate() {
        Collections.shuffle(numbers);
        List<LottoNumber> lottoNumbers = numbers.subList(FROM_INDEX, Lotto.LOTTO_NUMBER_SIZE);
        Collections.sort(lottoNumbers);
        return new Lotto(lottoNumbers);
    }
}

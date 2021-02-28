package lotto.utils;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomGenerator implements LottoGenerator {

    private List<LottoNumber> numbers = new ArrayList<>();

    public RandomGenerator() {
        initLottoNumber();
    }

    private void initLottoNumber() {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            numbers.add(LottoNumber.from(i));
        }
    }

    @Override
    public Lotto generate() {
        Collections.shuffle(numbers);
        List<LottoNumber> lottoNumbers = new ArrayList<>(numbers.subList(FROM_INDEX, Lotto.LOTTO_NUMBER_SIZE));
        Collections.sort(lottoNumbers);

        return new Lotto(lottoNumbers);
    }
}

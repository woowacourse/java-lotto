package lotto.utils;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static lotto.domain.LottoNumbers.LOTTO_NUMBER_SIZE;

public class AutoLottoGenerator implements LottoGenerator {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int FROM_INDEX = 0;

    private List<LottoNumber> numbers = new ArrayList<>();

    public AutoLottoGenerator() {
        createNumber();
    }

    private void createNumber() {
        IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .forEach(i -> numbers.add(new LottoNumber(Integer.toString(i))));
    }

    @Override
    public Lotto generate() {
        Collections.shuffle(numbers);
        List<LottoNumber> lottoNumbers = new ArrayList<>(numbers.subList(FROM_INDEX, LOTTO_NUMBER_SIZE));
        Collections.sort(lottoNumbers);
        return new Lotto(new LottoNumbers(lottoNumbers));
    }
}

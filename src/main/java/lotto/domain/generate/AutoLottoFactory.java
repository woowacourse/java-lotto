package lotto.domain.generate;

import lotto.domain.Lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.Lotto.LOTTO_NUMBER_SIZE;
import static lotto.domain.LottoNumber.MAX_LOTTO_NUMBER;
import static lotto.domain.LottoNumber.MIN_LOTTO_NUMBER;

public class AutoLottoFactory implements LottoFactory {
    private List<Integer> lottoNumbers;

    AutoLottoFactory() {
        this.lottoNumbers = generateLottoNumber();
    }

    private List<Integer> generateLottoNumber() {
        IntStream stream = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER);
        return stream.boxed().collect(Collectors.toList());
    }

    @Override
    public Lotto create() {
        Collections.shuffle(lottoNumbers);
        return new Lotto(lottoNumbers.subList(0, LOTTO_NUMBER_SIZE));
    }

}

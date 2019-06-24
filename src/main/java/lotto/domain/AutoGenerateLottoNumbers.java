package lotto.domain;

import lotto.util.AscendingNumber;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Collections.sort;
import static java.util.stream.Collectors.*;

public class AutoGenerateLottoNumbers extends Lotto {
    private static final List<LottoNumber> DEFAULT_LOTTO_NUMBERS;

    static {
        DEFAULT_LOTTO_NUMBERS = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(toList());
    }

    public AutoGenerateLottoNumbers() {
        this.lottoNumbers = invalidNumberOfLotto(makeAutoLottoNumbers());
        sort(lottoNumbers, new AscendingNumber());
    }

    private List<LottoNumber> makeAutoLottoNumbers() {
        Collections.shuffle(DEFAULT_LOTTO_NUMBERS);
        return DEFAULT_LOTTO_NUMBERS.stream()
                .limit(NUMBER_OF_LOTTO_NUMBER)
                .collect(toList());
    }

}
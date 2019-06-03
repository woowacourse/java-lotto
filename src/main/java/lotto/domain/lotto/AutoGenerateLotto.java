package lotto.domain.lotto;

import lotto.util.AscendingNumber;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoGenerateLotto extends Lotto {
    private static final List<LottoNumber> DEFAULT_LOTTO_NUMBERS;

    static {
        DEFAULT_LOTTO_NUMBERS = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public AutoGenerateLotto() {
        this.lottoNumbers = invalidNumberOfLotto(makeAutoLotto());
        Collections.sort(lottoNumbers, new AscendingNumber());
    }

    private List<LottoNumber> makeAutoLotto() {
        Collections.shuffle(DEFAULT_LOTTO_NUMBERS);
        return DEFAULT_LOTTO_NUMBERS.stream()
                .limit(NUMBER_OF_LOTTO_NUMBER)
                .collect(Collectors.toList());
    }

}
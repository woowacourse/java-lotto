package lotto.domain.lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.lotto.Lotto.*;

public class AutoGenerateLotto {
    private static final List<LottoNumber> DEFAULT_LOTTO_NUMBERS;

    static {
        DEFAULT_LOTTO_NUMBERS = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public AutoGenerateLotto(int numberOfPrice, List<Lotto> lottos) {
        for (int i = 0; i < numberOfPrice; i++) {
            lottos.add(makeAutoLotto());
        }
    }

    private Lotto makeAutoLotto() {
        Collections.shuffle(DEFAULT_LOTTO_NUMBERS);
        return new Lotto(DEFAULT_LOTTO_NUMBERS.stream()
                .limit(NUMBER_OF_LOTTO_NUMBER)
                .collect(Collectors.toList()));
    }
}
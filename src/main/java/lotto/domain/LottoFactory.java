package lotto.domain;

import java.util.List;

public class LottoFactory {
    public static Lotto createLotto(List<Number> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    public static Lotto createLotto(String[] numbers) {
        return new Lotto(numbers);
    }
}

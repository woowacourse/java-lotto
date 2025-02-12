package lotto.domain;

import java.util.List;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(final List<Integer> values) {
        this.numbers = makeNumber(values);
    }

    private List<LottoNumber> makeNumber(final List<Integer> values) {
        return values.stream()
                .map(LottoNumber::new)
                .toList();
    }
}

package lotto.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class FixedLottoGenerator implements LottoGenerator {

    private final List<Integer> numbers;

    public FixedLottoGenerator(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<LottoNumber> makeLottos() {
        return numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(toList());
    }
}

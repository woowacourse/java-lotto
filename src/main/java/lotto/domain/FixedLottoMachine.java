package lotto.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class FixedLottoMachine implements LottoMachine {

    private final List<Integer> numbers;

    public FixedLottoMachine(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<LottoNumber> makeLottos() {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(toList());
    }
}

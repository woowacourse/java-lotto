package lotto.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class FixedLottoMachine implements LottoMachine {

    private final List<Integer> numbers;

    public FixedLottoMachine(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Lotto makeLottos() {
        return new Lotto(numbers.stream()
                .map(LottoNumber::new)
                .collect(toList()));
    }
}

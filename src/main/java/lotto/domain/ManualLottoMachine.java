package lotto.domain;

import java.util.List;

public class ManualLottoMachine implements LottoMachine {

    private final List<Integer> numbers;

    public ManualLottoMachine(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Lotto generateLotto() {
        return new Lotto(numbers);
    }
}

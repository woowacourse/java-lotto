package domain;

import java.util.Set;

public class FixedLottoGenerator implements LottoGenerator {

    private final Set<LottoNumber> numbers;

    public FixedLottoGenerator(Set<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Lotto generateLotto() {
        return new Lotto(numbers);
    }
}

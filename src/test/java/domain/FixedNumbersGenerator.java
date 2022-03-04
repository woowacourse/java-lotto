package domain;

import java.util.Set;

public class FixedNumbersGenerator implements LottoGenerator {

    private final Set<LottoNumber> numbers;

    public FixedNumbersGenerator(Set<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Lotto generateLotto() {
        return new Lotto(numbers);
    }
}

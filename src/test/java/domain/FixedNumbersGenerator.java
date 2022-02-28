package domain;

import java.util.Set;

public class FixedNumbersGenerator implements LottoNumbersGenerator {

    private final Set<LottoNumber> numbers;

    public FixedNumbersGenerator(Set<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Set<LottoNumber> generateNumbers() {
        return numbers;
    }
}

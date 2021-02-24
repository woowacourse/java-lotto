package lotto.domain.generator;

import java.util.Set;

public class FixedNumberGenerator implements LottoNumberGenerator {
    
    private final Set<Integer> numbers;
    
    public FixedNumberGenerator(Set<Integer> numbers) {
        this.numbers = numbers;
    }
    
    @Override
    public Set<Integer> generateNumbers() {
        return numbers;
    }
}

package domain;

import domain.lottogeneratestrategy.LottoPickStrategy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoMachine {

    private final int lottoNumberSize;
    private final LottoPickStrategy numberStrategy;

    public LottoMachine(LottoPickStrategy numberStrategy, int lottoNumberSize) {
        this.numberStrategy = numberStrategy;
        this.lottoNumberSize = lottoNumberSize;
    }

    public Lotto createLotto() {
        Set<Number> numbers;
        do {
            numbers = new HashSet<>(selectNumbers());
        } while (numbers.size() != lottoNumberSize);
        return new Lotto(numbers);
    }

    private List<Number> selectNumbers() {
        List<Integer> numbers = numberStrategy.pickNumbers(lottoNumberSize);
        return numbers.stream().map(Number::new).toList();
    }
}

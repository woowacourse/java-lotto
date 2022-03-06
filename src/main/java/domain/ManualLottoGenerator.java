package domain;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {
    private final String[] numbers;

    public ManualLottoGenerator(final String[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public Lotto generateLotto() {
        Set<LottoNumber> collect = Arrays.stream(numbers)
                .map(Integer::parseInt)
                .map(LottoNumber::values)
                .collect(Collectors.toSet());
        return new Lotto(collect);
    }
}

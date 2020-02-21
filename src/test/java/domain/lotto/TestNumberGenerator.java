package domain.lotto;

import domain.lotto.generator.NumberGenerator;

import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestNumberGenerator implements NumberGenerator {
    private final SortedSet<LottoNumber> numbers;

    public TestNumberGenerator() {
        numbers = IntStream.range(1, 7)
                .mapToObj(LottoNumberFactory::getInstance)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TestNumberGenerator(List<Integer> values) {
        if (Objects.isNull(values)) {
            throw new IllegalArgumentException("null값은 입력할 수 없습니다.");
        }
        this.numbers = values.stream()
                .map(LottoNumberFactory::getInstance)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public SortedSet<LottoNumber> create() {
        return numbers;
    }
}

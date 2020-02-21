package domain.lottonumber;

import domain.lottonumber.generator.NumberGenerator;

import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestNumberGenerator implements NumberGenerator {
    private final SortedSet<LottoNumber> numbers = new TreeSet<>();

    public TestNumberGenerator() {
        for (int i = 1; i <= 6; i++) {
            numbers.add(LottoNumber.of(i));
        }
    }

    public TestNumberGenerator(List<Integer> values) {
        if (Objects.isNull(values)) {
            throw new IllegalArgumentException("null값은 입력할 수 없습니다.");
        }
        for (int number : values) {
            numbers.add(LottoNumber.of(number));
        }
    }

    @Override
    public SortedSet<LottoNumber> create() {
        return numbers;
    }
}

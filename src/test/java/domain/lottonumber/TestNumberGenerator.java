package domain.lottonumber;

import domain.lottonumber.generator.NumberGenerator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestNumberGenerator implements NumberGenerator {
    private final List<LottoNumber> numbers;

    public TestNumberGenerator() {
        numbers = IntStream.range(1, 7)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
    }

    public TestNumberGenerator(List<Integer> values) {
        if (Objects.isNull(values)) {
            throw new IllegalArgumentException("null값은 입력할 수 없습니다.");
        }
        this.numbers = values.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumber> create() {
        return numbers;
    }
}

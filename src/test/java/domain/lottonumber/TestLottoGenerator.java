package domain.lottonumber;

import domain.lottonumber.generator.LottoGenerator;

import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class TestLottoGenerator implements LottoGenerator {
    private final SortedSet<LottoNumber> numbers = new TreeSet<>();

    public TestLottoGenerator() {
        for (int i = 1; i <= 6; i++) {
            numbers.add(LottoNumber.of(i));
        }
    }

    public TestLottoGenerator(List<Integer> values) {
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

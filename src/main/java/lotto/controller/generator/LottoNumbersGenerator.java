package lotto.controller.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbersGenerator implements NumbersGenerator {

    private static final int MIN_NUMBERS_SIZE = 0;
    private final int startInclusive;
    private final int endInclusive;
    private final int size;

    public LottoNumbersGenerator(final int startInclusive, final int endInclusive, final int size) {
        validateSize(size);
        validateRange(startInclusive, endInclusive, size);
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
        this.size = size;
    }

    private void validateSize(final int size) {
        if (size < MIN_NUMBERS_SIZE) {
            throw new IllegalArgumentException("번호 개수가 %d보다 작을 수 없습니다.".formatted(MIN_NUMBERS_SIZE));
        }
    }

    private void validateRange(final int startInclusive, final int endInclusive, final int size) {
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException("최소값이 최대값보다 클 수 없습니다.");
        }
        if ((endInclusive - startInclusive + 1) < size) {
            throw new IllegalArgumentException("번호 범위가 개수보다 작을 수 없습니다.");
        }
    }

    @Override
    public List<Integer> generate() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int number = startInclusive; number <= endInclusive; number++) {
            lottoNumbers.add(number);
        }
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(MIN_NUMBERS_SIZE, size);
    }

}

package lotto.controller.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbersGenerator implements NumbersGenerator {

    private final int startInclusive;
    private final int endInclusive;
    private final int size;

    public LottoNumbersGenerator(final int startInclusive, final int endInclusive, final int size) {
        validateRange(startInclusive, endInclusive);
        validateSize(size);
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
        this.size = size;
    }

    private void validateRange(final int startInclusive, final int endInclusive) {
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException("최소값이 최대값보다 클 수 없습니다.");
        }
    }

    private void validateSize(final int size) {
        if (size < 0) {
            throw new IllegalArgumentException("번호 개수가 0보다 작을 수 없습니다.");
        }
    }

    @Override
    public List<Integer> generate() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int number = startInclusive; number <= endInclusive; number++) {
            lottoNumbers.add(number);
        }
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(0, size);
    }

}

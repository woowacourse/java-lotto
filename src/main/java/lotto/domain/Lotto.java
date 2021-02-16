package lotto.domain;

import java.util.List;

public class Lotto {
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int countMatchingNumbers(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(lottoNumbers::contains)
                .count();
    }
}

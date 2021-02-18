package lotto.domain;

import com.google.common.primitives.Ints;

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

    public boolean hasBonusNumber(int bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public String getLottoSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(Ints.join(", ", lottoNumbers.stream().mapToInt(i -> i).toArray()));
        sb.append("]");

        return sb.toString();
    }
}

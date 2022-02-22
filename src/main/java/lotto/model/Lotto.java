package lotto.model;

import java.util.List;

public class Lotto {
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int getSize() {
        return lottoNumbers.size();
    }

    public int match(List<Integer> winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean matchBonusNumber(Integer bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }
}

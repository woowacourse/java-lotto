package lotto.model;

import java.util.List;

public class LottoMatcher {
    private final List<Integer> winningNumbers;

    public LottoMatcher(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public int match(Lotto lotto) {
        return lotto.match(winningNumbers);
    }
}

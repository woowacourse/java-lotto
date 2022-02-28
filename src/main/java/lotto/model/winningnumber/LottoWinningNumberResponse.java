package lotto.model.winningnumber;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LottoWinningNumberResponse {
    private final Set<Integer> winningNumbers;

    public LottoWinningNumberResponse(Set<Integer> winningNumbers) {
        this.winningNumbers = new HashSet<>(winningNumbers);
    }

    public Set<Integer> getWinningNumbers() {
        return Collections.unmodifiableSet(winningNumbers);
    }
}

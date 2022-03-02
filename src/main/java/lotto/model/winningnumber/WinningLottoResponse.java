package lotto.model.winningnumber;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class WinningLottoResponse {
    private final Set<Integer> winningNumbers;
    private final int bonusBall;

    public WinningLottoResponse(Set<Integer> winningNumbers, int bonusBall) {
        this.winningNumbers = new HashSet<>(winningNumbers);
        this.bonusBall = bonusBall;
    }

    public Set<Integer> getWinningNumbers() {
        return Collections.unmodifiableSet(winningNumbers);
    }

    public int getBonusBall() {
        return bonusBall;
    }
}

package lotto.model.winningnumber;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class WinningLottoResponse {
    private final Set<Integer> winningNumbers;
    private final int bonusBall;

    public WinningLottoResponse(WinningLotto winningLotto) {
        this.winningNumbers = new HashSet<>(winningLotto.getNumbers());
        this.bonusBall = winningLotto.getBonusBall();
    }

    public Set<Integer> getWinningNumbers() {
        return Collections.unmodifiableSet(winningNumbers);
    }

    public int getBonusBall() {
        return bonusBall;
    }
}

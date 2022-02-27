package lotto.model;

import java.util.List;

public class WinningNumbers {
    private final Lotto winningNumbers;

    public WinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = new Lotto(winningNumbers);
    }

    boolean matchNumber(LottoNumber number) {
        return winningNumbers.isMatchNumber(number);
    }
}

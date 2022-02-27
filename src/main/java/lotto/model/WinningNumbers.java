package lotto.model;

import java.util.List;

public class WinningNumbers {
    private final Lotto winningNumbers;

    public WinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = new Lotto(winningNumbers);
    }

    public boolean isNumberMatch(LottoNumber number) {
        return winningNumbers.isNumberMatch(number);
    }
}

package lotto;

import java.util.List;

public class WinningNumbers {
    private List<Integer> winningNumbers;
    private int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public boolean match(int number) {
        return winningNumbers.contains(number);
    }

    public boolean matchBonusNumber(Lotto lotto) {
        return lotto.contains(this.bonusNumber);
    }
}

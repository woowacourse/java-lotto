package model;

import java.util.List;

public class LottoWinningNumbers {
    private final List<Integer> winningNumber;
    private final int bonusNumber;

    public LottoWinningNumbers(List<Integer> numbers, int bonusNumber) {
        this.winningNumber = numbers;
        this.bonusNumber = bonusNumber;
    }
    
    public List<Integer> getWinningNumber() {
        return winningNumber;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
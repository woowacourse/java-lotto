package model;

import java.util.List;

public class LottoWinningNumbers {
    private final List<Integer> numbers;
    private final int bonusNumber;

    public LottoWinningNumbers(List<Integer> numbers, int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
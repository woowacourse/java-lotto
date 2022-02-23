package lotto.domain;

public class WinningLotto {
    private final PickedNumbers winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningLotto(PickedNumbers pickedNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = pickedNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int findSameNumbersInPicked(PickedNumbers pickedNumbers) {
        return winningNumbers.findMatchCount(pickedNumbers);
    }
}

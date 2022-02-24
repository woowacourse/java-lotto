package lotto.domain;

public class WinningLotto {
    private final PickedNumbers winningNumbers;
    private final BonusNumber bonusNumber;
    private static final int SECOND_OR_THIRD_COUNT = 5;

    public WinningLotto(PickedNumbers pickedNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = pickedNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int findSameNumbersInPicked(PickedNumbers pickedNumbers) {
        return winningNumbers.findMatchCount(pickedNumbers);
    }

    public LottoRank findLottoRank(PickedNumbers pickedNumbers) {
        int correctCount = findSameNumbersInPicked(pickedNumbers);
        boolean isBonused = false;
        if (isSecondOrThird(correctCount)) {
            isBonused = pickedNumbers.isContainNumber(bonusNumber.getBonusNumber());
        }
        return LottoRank.valueOf(correctCount, isBonused);
    }

    private boolean isSecondOrThird(int correctCount) {
        if (correctCount == SECOND_OR_THIRD_COUNT) {
            return true;
        }
        return false;
    }
}


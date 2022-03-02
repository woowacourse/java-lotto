package lotto.domain;

public class WinningLotto {
    private static final int SECOND_OR_THIRD_COUNT = 5;
    private final PickedNumbers winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningLotto(PickedNumbers pickedNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = pickedNumbers;
        this.bonusNumber = bonusNumber;
    }

    public long findSameNumbersInPicked(PickedNumbers pickedNumbers) {
        return winningNumbers.findMatchCount(pickedNumbers);
    }

    public void addLottoResult(PickedNumbers pickedNumbers, LottoResult result) {
        long correctCount = findSameNumbersInPicked(pickedNumbers);
        boolean isBonused = false;
        if (isSecondOrThird(correctCount)) {
            isBonused = pickedNumbers.isContainNumber(bonusNumber.getBonusNumber());
        }
        LottoRank.addLottoResult(result, correctCount, isBonused);
    }

    private boolean isSecondOrThird(long correctCount) {
        return correctCount == SECOND_OR_THIRD_COUNT;
    }
}


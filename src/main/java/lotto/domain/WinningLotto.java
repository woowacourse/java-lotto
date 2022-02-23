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

    public LottoRank findLottoRank(PickedNumbers pickedNumbers) {
        int sameCount = findSameNumbersInPicked(pickedNumbers);
        boolean isBonused = false;
        if (sameCount == 5) {
            isBonused = pickedNumbers.isContainNumber(bonusNumber.getBonusNumber());
        }

        return LottoRank.valueOf(sameCount, isBonused);
    }
}


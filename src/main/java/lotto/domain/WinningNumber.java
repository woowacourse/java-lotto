package lotto.domain;

public class WinningNumber {
    private final ChoiceNumber winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningNumber(ChoiceNumber choiceNumber, BonusNumber bonusNumber) {
        this.winningNumbers = choiceNumber;
        this.bonusNumber = bonusNumber;
    }

    public int findSameNumbersInPicked(ChoiceNumber choiceNumber) {
        return winningNumbers.findMatchCount(choiceNumber);
    }

    public LottoRank findLottoRank(ChoiceNumber choiceNumber) {
        int sameCount = findSameNumbersInPicked(choiceNumber);
        boolean isBonused = false;
        if (sameCount == 5) {
            isBonused = choiceNumber.isContainNumber(bonusNumber.getBonusNumber());
        }

        return LottoRank.valueOf(sameCount, isBonused);
    }
}


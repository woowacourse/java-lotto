package lotto.domain;

public class WinningNumber {
    private final ChoiceNumber winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningNumber(ChoiceNumber choiceNumber, BonusNumber bonusNumber) {
        this.winningNumbers = choiceNumber;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank findLottoRank(ChoiceNumber choiceNumber) {
        int sameCount = findSameNumber(choiceNumber);
        boolean isBonused = false;
        if (sameCount == 5) {
            isBonused = choiceNumber.isContainNumber(bonusNumber.getBonusNumber());
        }

        return LottoRank.valueOf(sameCount, isBonused);
    }

    private int findSameNumber(ChoiceNumber choiceNumber) {
        return winningNumbers.countSameNumber(choiceNumber);
    }
}


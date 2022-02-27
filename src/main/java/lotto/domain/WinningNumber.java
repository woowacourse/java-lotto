package lotto.domain;

public class WinningNumber {
    private final ChoiceNumber winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningNumber(ChoiceNumber choiceNumber, BonusNumber bonusNumber) {
        this.winningNumbers = choiceNumber;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank findLottoRank(ChoiceNumber choiceNumber) {
        int sameCount = findSameValueWith(choiceNumber);
        boolean isBonus = containsBonusNumber(choiceNumber);

        return LottoRank.valueOf(sameCount, isBonus);
    }

    private boolean containsBonusNumber(ChoiceNumber choiceNumber) {
        return choiceNumber.contains(bonusNumber.getBonusNumber());
    }

    private int findSameValueWith(ChoiceNumber choiceNumber) {
        return winningNumbers.countSameNumber(choiceNumber);
    }
}


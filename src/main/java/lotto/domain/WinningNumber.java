package lotto.domain;

import java.util.List;

public class WinningNumber {
    private final Lotto winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningNumber(Lotto lotto, BonusNumber bonusNumber) {
        checkDuplicate(lotto.getLotto(), bonusNumber.getBonusNumber());
        this.winningNumbers = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void checkDuplicate(List<Integer> lotto, int value) {
        if (lotto.contains(value)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 선택한 숫자들과 중복한 값입니다.");
        }
    }

    public LottoRank findLottoRank(Lotto lotto) {
        int sameCount = findSameValueWith(lotto);
        boolean isBonus = containsBonusNumber(lotto);
        return LottoRank.valueOf(sameCount, isBonus);
    }

    private boolean containsBonusNumber(Lotto lotto) {
        return lotto.contains(bonusNumber.getBonusNumber());
    }

    private int findSameValueWith(Lotto lotto) {
        return winningNumbers.countSameNumber(lotto);
    }
}


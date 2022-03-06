package lotto.domain;

import java.util.List;

public class WinningNumber {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumber(Lotto lotto, LottoNumber bonusNumber) {
        checkDuplicate(lotto.getLotto(), bonusNumber);
        this.winningNumbers = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void checkDuplicate(List<LottoNumber> lotto, LottoNumber lottoNumber) {
        if (lotto.contains(lottoNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 선택한 숫자들과 중복한 값입니다.");
        }
    }

    public LottoRank findLottoRank(Lotto lotto) {
        int sameCount = findSameValueWith(lotto);
        boolean isBonus = containsBonusNumber(lotto);
        return LottoRank.valueOf(sameCount, isBonus);
    }

    private boolean containsBonusNumber(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }

    private int findSameValueWith(Lotto lotto) {
        return winningNumbers.countSameNumber(lotto);
    }
}


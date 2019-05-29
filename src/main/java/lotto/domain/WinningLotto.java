package lotto.domain;

import lotto.exceptions.BonusNumberException;

public class WinningLotto {
    private static final int COUNT_INITIALIZE = 0;

    private final WinningNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        if (winningNumbers.contains(bonusNumber)) {
            throw new BonusNumberException("당첨 번호와 중복될 수 없습니다.");
        }
        this.bonusNumber = bonusNumber;
    }

    public Rank getRank(Lotto lotto) {
        int matchCount = COUNT_INITIALIZE;
        boolean bonusMatch = lotto.contains(bonusNumber);
        for (Number number : lotto) {
            matchCount = countAdder(matchCount, (LottoNumber) number);
        }
        return Rank.valueOf(matchCount, bonusMatch);
    }

    private int countAdder(int matchCount, LottoNumber lottoNumber) {
        if (winningNumbers.contains(lottoNumber)) {
            matchCount++;
        }
        return matchCount;
    }
}

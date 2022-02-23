package lotto.domain;

import java.util.List;

public class Lotto {

    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        this.lotto = lotto;
    }

    private int getWinningNumbersMatchCount(WinningNumbers winningNumbers) {
        return winningNumbers.getMatchCount(lotto);
    }

    private boolean isBonusNumberMatch(BonusNumber bonusNumber) {
        return bonusNumber.isMatch(lotto);
    }

    public Rank getRank(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        int winningNumberMatchCount = getWinningNumbersMatchCount(winningNumbers);
        boolean bonusNumberMatch = isBonusNumberMatch(bonusNumber);
        return Rank.getRank(winningNumberMatchCount, bonusNumberMatch);
    }
}

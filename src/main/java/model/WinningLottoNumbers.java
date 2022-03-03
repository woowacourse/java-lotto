package model;

import exception.DuplicatedLottoNumbersException;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoNumbers {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLottoNumbers(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new DuplicatedLottoNumbersException();
        }
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank getRankBy(Lotto lotto) {
        int count = getMatchedCountAboutWinningNumbers(lotto);
        boolean bonusMatch = isBonusMatch(lotto);
        return LottoRank.of(count, bonusMatch);
    }

    private int getMatchedCountAboutWinningNumbers(Lotto lotto) {
        return this.lotto.getMatchedNumberCountWith(lotto);
    }

    private boolean isBonusMatch(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}

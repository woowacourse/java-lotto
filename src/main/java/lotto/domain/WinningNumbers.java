package lotto.domain;

import java.util.List;
import lotto.validator.BonusNumberValidator;

public class WinningNumbers {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    private WinningNumbers(Lotto winningLotto, LottoNumber bonusNumber) {
        BonusNumberValidator.validate(bonusNumber, winningLotto);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers generate(List<Integer> winningLotto, int bonusNumber) {
        return new WinningNumbers(
                Lotto.generateByManual(winningLotto),
                LottoNumber.findByNumber(bonusNumber)
        );
    }

    public Rank findRankOf(Lotto lotto) {
        return Rank.findRank(lotto.getMatchCount(winningLotto), lotto.isContain(bonusNumber));
    }
}

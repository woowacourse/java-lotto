package lotto.domain;

import java.util.List;
import lotto.validator.BonusNumberValidator;

public class WinningNumbers {

    private final Lotto winningLotto;
    private final LottoNumber lottoNumber;

    private WinningNumbers(Lotto winningLotto, LottoNumber lottoNumber) {
        BonusNumberValidator.validate(lottoNumber, winningLotto);
        this.winningLotto = winningLotto;
        this.lottoNumber = lottoNumber;
    }

    public static WinningNumbers generate(List<Integer> winningLotto, int lottoNumber) {
        return new WinningNumbers(
                Lotto.generateByManual(winningLotto),
                LottoNumber.findByNumber(lottoNumber)
        );
    }

    public Rank findRankOf(Lotto lotto) {
        return Rank.findRank(lotto.getMatchCount(winningLotto), lotto.isContain(lottoNumber));
    }
}

package lotto.domain;

import lotto.validator.BonusNumberValidator;

public class WinningNumbers {

    private final Lotto winningLotto;
    private final LottoNumber lottoNumber;

    private WinningNumbers(Lotto winningLotto, LottoNumber lottoNumber) {
        BonusNumberValidator.validate(lottoNumber, winningLotto);
        this.winningLotto = winningLotto;
        this.lottoNumber = lottoNumber;
    }

    public static WinningNumbers generateByString(String winningLotto, String lottoNumber) {
        return new WinningNumbers(
                Lotto.generateByManual(winningLotto),
                LottoNumber.findByNumber(Integer.parseInt(lottoNumber))
        );
    }

    public Rank findRankOf(Lotto lotto) {
        return Rank.findRank(lotto.getMatchCount(winningLotto), lotto.isContain(lottoNumber));
    }
}

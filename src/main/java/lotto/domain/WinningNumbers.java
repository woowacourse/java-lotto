package lotto.domain;

import lotto.validator.BonusNumberValidator;

public class WinningNumbers {

    private final Lotto winningLotto;
    private final LottoNumber lottoNumber;

    private WinningNumbers(Lotto winningLotto, LottoNumber lottoNumber) {
        this.winningLotto = winningLotto;
        BonusNumberValidator.validate(lottoNumber, winningLotto);
        this.lottoNumber = lottoNumber;
    }

    public static WinningNumbers generateWinningNumbersByString(String winningLotto, String lottoNumber) {
        return new WinningNumbers(Lotto.generateLottoByString(winningLotto),
                LottoNumber.findByNumber(Integer.parseInt(lottoNumber)));
    }

    public Rank getRankOf(Lotto lotto) {
        int matchCount = lotto.getMatchCount(winningLotto);
        boolean contains = lotto.isContain(lottoNumber);
        return Rank.getRank(matchCount, contains);
    }
}

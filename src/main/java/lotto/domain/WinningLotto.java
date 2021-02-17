package lotto.domain;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusBall;

    public WinningLotto(final Lotto lotto, final LottoNumber bonusBall) {
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    public int compareLottoNumber(final Lotto lotto) {
        return lotto.countCommonValue(this.lotto);
    }

    public boolean compareBonusBall(final Lotto lotto) {
        return lotto.containNumber(bonusBall);
    }
}

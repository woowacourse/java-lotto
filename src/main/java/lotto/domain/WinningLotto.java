package lotto.domain;

public class WinningLotto {
    private final Lotto lotto;
    private final int bonusBall;

    public WinningLotto(Lotto lotto, int bonusBall) {
        if (lotto.isContainLottoNumber(LottoNumber.getLottoNumber(bonusBall))) {
            throw new IllegalArgumentException("당첨번호와 일치하는 보너스볼은 안됩니다.");
        }
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    @Override
    public String toString() {
        return lotto.toString() + "\n" + bonusBall;
    }

    public int getMatchCount(Lotto lotto) {
        return this.lotto.getMatchCount(lotto);
    }

    public boolean isMatchBonus(Lotto lotto) {
        return lotto.isContainLottoNumber(LottoNumber.getLottoNumber(bonusBall));
    }
}

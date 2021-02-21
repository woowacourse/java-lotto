package domain;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusBall;

    public WinningLotto(Lotto lotto, LottoNumber bonusBall) {
        validateWinningLotto(lotto, bonusBall);

        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    private void validateWinningLotto(Lotto lotto, LottoNumber bonusBall) {
        if (lotto.contains(bonusBall)) {
            throw new IllegalArgumentException("당첨 번호 안에 보너스 볼이 포함됩니다.");
        }
    }

    public int findMatchCount(Lotto target) {
        return lotto.findMatchCount(target);
    }

    public boolean isBonusBallMatch(Lotto target) {
        return target.contains(bonusBall);
    }
}

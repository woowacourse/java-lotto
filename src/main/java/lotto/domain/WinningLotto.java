package lotto.domain;

public class WinningLotto {
    private static final String ERROR_DUPLICATED_NUMBER = "번호가 중복됩니다!";

    private final Lotto winningLotto;
    private final LottoNumber bonusBall;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusBall) {
        checkDuplicatedNumber(winningLotto, bonusBall);
        this.winningLotto = winningLotto;
        this.bonusBall = bonusBall;
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public LottoNumber getBonusBall() {
        return bonusBall;
    }

    private void checkDuplicatedNumber(Lotto lotto, LottoNumber ball) {
        if (lotto.contains(ball)) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NUMBER);
        }
    }
}

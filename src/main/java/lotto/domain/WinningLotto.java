package lotto.domain;

public class WinningLotto {
    private static final String ERROR_DUPLICATED_NUMBER = "번호가 중복됩니다!";

    private final Lotto winningLotto;
    private final Ball bonusBall;

    public WinningLotto(Lotto winningLotto, Ball bonusBall) {
        validateDuplicatedNumber(winningLotto, bonusBall);
        this.winningLotto = winningLotto;
        this.bonusBall = bonusBall;
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public Ball getBonusBall() {
        return bonusBall;
    }

    private void validateDuplicatedNumber(Lotto lotto, Ball ball) {
        if (lotto.contains(ball)) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NUMBER);
        }
    }
}

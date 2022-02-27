package lotto.domain;

public class WinningLotto {
    private static final String ERROR_NULL = "올바른 값을 입력해주세요!";
    private static final String ERROR_DUPLICATED_NUMBER = "번호가 중복됩니다!";

    private final Lotto winningNumbers;
    private final Ball bonusBall;

    public WinningLotto(final Lotto winningNumbers, final Ball bonusBall) {
        validateWinningLotto(winningNumbers, bonusBall);
        this.winningNumbers = winningNumbers;
        this.bonusBall = bonusBall;
    }

    public Lotto getWinningNumbers() {
        return winningNumbers;
    }

    public Ball getBonusBall() {
        return bonusBall;
    }

    private void validateWinningLotto(final Lotto winningNumbers, final Ball bonusBall) {
        validateNull(winningNumbers, bonusBall);
        validateDuplicatedNumber(winningNumbers, bonusBall);
    }

    private void validateNull(final Lotto lotto, final Ball ball) {
        if (lotto == null || ball == null) {
            throw new IllegalArgumentException(ERROR_NULL);
        }
    }

    private void validateDuplicatedNumber(final Lotto lotto, final Ball ball) {
        if (lotto.contains(ball)) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NUMBER);
        }
    }
}

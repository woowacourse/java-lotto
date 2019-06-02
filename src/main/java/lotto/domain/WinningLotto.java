package lotto.domain;

public class WinningLotto {
    private static final String ERROR_DUPLICATE = "당첨 번호와 중복되었습니다.";

    private final Lotto winnerLotto;
    private final Number bonus;

    public WinningLotto(Lotto winnerLotto, Number bonus) {
        this.winnerLotto = winnerLotto;
        this.bonus = bonus;

        checkDuplicate();
    }

    private void checkDuplicate() {
        if (winnerLotto.isContains(bonus)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    @Override
    public String toString() {
        return "WinningLotto{" +
                "winnerLotto=" + winnerLotto +
                ", bonus=" + bonus +
                '}';
    }
}

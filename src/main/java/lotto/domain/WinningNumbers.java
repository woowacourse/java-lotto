package lotto.domain;

import lotto.exception.InvalidInputException;

public class WinningNumbers {
    private final Lotto winningLotto;
    private final Ball bonusBall;

    public WinningNumbers(String winningLottoInput, String bonusBallInput) {
        Lotto winningLotto = new Lotto(winningLottoInput);
        Ball bonusBall = new Ball(bonusBallInput);
        checkDuplicate(winningLotto, bonusBall);
        this.winningLotto = winningLotto;
        this.bonusBall = bonusBall;
    }

    private void checkDuplicate(Lotto winningLotto, Ball bonusBall) {
        if (winningLotto.contains(bonusBall)) {
            throw new InvalidInputException("당첨 번호와 보너스 볼은 중복될 수 없습니다.");
        }
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public Ball getBonusBall() {
        return bonusBall;
    }
}

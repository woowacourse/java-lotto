package domain.winning;

import domain.lotto.Ball;
import domain.lotto.Lotto;
import java.util.List;

public class WinningLotto {

    private final Lotto lotto;
    private final Ball bonusBall;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.lotto = new Lotto(winningNumbers);
        validateUniqueBall(bonusNumber);
        this.bonusBall = new Ball(bonusNumber);
    }

    public List<Integer> getWinningNumbers() {
        return lotto.getBallNumbers();
    }

    public Integer getBonusNumber() {
        return bonusBall.getNumber();
    }

    private void validateUniqueBall(int bonusNumber) {
        if (lotto.getBallNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

}

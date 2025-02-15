package domain;

import java.util.List;

public class WinningLotto {

    private final Lotto lotto;
    private final Ball bonusBall;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.lotto = new Lotto(winningNumbers);
        validateUniqueBall(bonusNumber);
        this.bonusBall = new Ball(bonusNumber);

    }

    private void validateUniqueBall(int bonusNumber) {
        if (lotto.getBallNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public List<Integer> getWinningNumbers() {
        return lotto.getBalls().stream()
                .map(Ball::getNumber)
                .toList();
    }

    public Integer getBonusNumber() {
        return bonusBall.getNumber();
    }
}

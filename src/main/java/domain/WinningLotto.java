package domain;

import domain.vo.Number;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final Number bonusNumber;

    public WinningLotto(Lotto winningNumbers, Number bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public boolean isMatchWinningNumber(Number number) {
        return winningNumbers.isExist(number);
    }

    public boolean isMatchBonus(Number number) {
        return bonusNumber.equals(number);
    }

    private void validate(Lotto winningNumbers, Number bonusNumber) {
        if (winningNumbers.isExist(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }
}

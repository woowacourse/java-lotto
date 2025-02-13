import java.util.Objects;

public class WinningLotto {
    Lotto winningNumbers;
    Number bonusNumber;

    public WinningLotto(Lotto winningNumbers, Number bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    // 중복 확인
    private void validate(Lotto winningNumbers, Number bonusNumber) {
        if (winningNumbers.getNumbers().stream().anyMatch(number -> number == bonusNumber.getValue())) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

    public int getMatchedCount(Lotto lotto) {
        int matchCount = 0;
        for (Integer number : lotto.getNumbers()) {
            if (isExistWinningNumber(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    public boolean isMatchBonus(Lotto lotto) {
        for (Integer number : lotto.getNumbers()) {
            if (number == bonusNumber.getValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean isExistWinningNumber(final int value) {
        for (Integer winningNumber : winningNumbers.getNumbers()) {
            if (Objects.equals(winningNumber, value)) {
                return true;
            }
        }
        return false;
    }
}

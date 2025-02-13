import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WinningLotto {
    Lotto winningNumbers;
    Number bonusNumber;

    public WinningLotto(Lotto winningNumbers, Number bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int getMatchedCount(Lotto lotto) {
        List<Integer> lottoNumbers = new ArrayList<>(lotto.getNumbers());
        lottoNumbers.retainAll(winningNumbers.getNumbers());
        return lottoNumbers.size();
    }

    public boolean isMatchBonus(Lotto lotto) {
        return lotto.getNumbers().stream().anyMatch(number -> number == bonusNumber.getValue());
    }

    private void validate(Lotto winningNumbers, Number bonusNumber) {
        if (winningNumbers.getNumbers().stream().anyMatch(number -> number == bonusNumber.getValue())) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
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

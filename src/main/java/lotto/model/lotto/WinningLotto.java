package lotto.model.lotto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import lotto.model.message.BonusBallExceptionMessage;
import lotto.model.message.LottoNumberExceptionMessage;

import static lotto.model.lotto.LottoNumbers.MAX;
import static lotto.model.lotto.LottoNumbers.MIN;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final int bonusBall;

    public WinningLotto(List<Integer> numbers, int bonusBall) {
        this.winningNumbers = new Lotto(numbers);
        validateBonusBall(bonusBall);
        this.bonusBall = bonusBall;
    }

    private void validateBonusBall(int bonusBall) {
        inputOutOfRange(bonusBall, BonusBallExceptionMessage.RANGE_ERROR.getMessage());
        validateReduplicationWithBonusBall(bonusBall);
    }

    private void inputOutOfRange(int number, String message) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException(message);
        }
    }

    public void validateReduplicationWithBonusBall(int number) {
        if (winningNumbers.getNumbers().contains(number)) {
            throw new IllegalArgumentException(LottoNumberExceptionMessage.REDUPLICATION_BONUS_BALL_ERROR.getMassage());
        }
    }

    public Set<Integer> getNumbers() {
        return Collections.unmodifiableSet(winningNumbers.getNumbers());
    }

    public int getBonusBall() {
        return bonusBall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return bonusBall == that.bonusBall && Objects.equals(winningNumbers, that.winningNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumbers, bonusBall);
    }
}

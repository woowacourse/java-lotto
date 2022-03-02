package lotto.model.winningnumber;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import lotto.model.lotto.Lotto;
import lotto.model.message.BonusBallExceptionMessage;
import lotto.model.message.LottoNumberExceptionMessage;
import lotto.utils.ConverterUtils;
import lotto.utils.InputValidateUtils;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final int bonusBall;

    public WinningLotto(List<String> numbers, String bonusBall) {
        this.winningNumbers = new Lotto(numbers);
        validateBonusBall(bonusBall);
        this.bonusBall = ConverterUtils.convertStringToInt(bonusBall);
    }

    private void validateBonusBall(String bonusBall) {
        InputValidateUtils.inputBlank(bonusBall, BonusBallExceptionMessage.BLANK_ERROR.getMessage());
        InputValidateUtils.inputNumber(bonusBall, BonusBallExceptionMessage.NUMBER_ERROR.getMessage());
        InputValidateUtils.inputOutOfRange(bonusBall, BonusBallExceptionMessage.RANGE_ERROR.getMessage());
        validateReduplicationWithBonusBall(bonusBall);
    }

    public void validateReduplicationWithBonusBall(String number) {
        if (winningNumbers.getNumbers().contains(ConverterUtils.convertStringToInt(number))) {
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

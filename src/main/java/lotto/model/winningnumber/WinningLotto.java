package lotto.model.winningnumber;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.model.message.BonusBallExceptionMessage;
import lotto.model.message.WinningNumberExceptionMessage;
import lotto.utils.ConverterUtils;
import lotto.utils.InputValidateUtils;

public class WinningLotto {
    private static final int WINNING_NUMBER_SIZE = 6;
    private static final String CONCAT = "";

    private final Set<Integer> winningNumbers;
    private final int bonusBall;

    public WinningLotto(List<String> numbers, String bonusBall) {
        validateWinningNumbers(numbers);
        this.winningNumbers = makeWinningNumbers(numbers);
        validateBonusBall(bonusBall);
        this.bonusBall = ConverterUtils.convertStringToInt(bonusBall);
    }

    private void validateWinningNumbers(List<String> numbers) {
        inputBlank(numbers);
        InputValidateUtils.inputNumber(makeNumbersToString(numbers), WinningNumberExceptionMessage.NUMBER_ERROR.getMassage());
        validateNumberOutOfRange(numbers);
        validateNumberSize(numbers);
        validateNumberReduplication(numbers);
    }

    private void validateBonusBall(String bonusBall) {
        InputValidateUtils.inputBlank(bonusBall, BonusBallExceptionMessage.BLANK_ERROR.getMessage());
        InputValidateUtils.inputNumber(bonusBall, BonusBallExceptionMessage.NUMBER_ERROR.getMessage());
        InputValidateUtils.inputOutOfRange(bonusBall, BonusBallExceptionMessage.RANGE_ERROR.getMessage());
        validateReduplicationWithBonusBall(bonusBall);
    }

    private void inputBlank(List<String> numbers) {
        if (numbers == null || isBlankNumbers(numbers)) {
            throw new IllegalArgumentException(WinningNumberExceptionMessage.BLANK_ERROR.getMassage());
        }
    }

    private boolean isBlankNumbers(List<String> numbers) {
        return numbers.stream().anyMatch(String::isBlank);
    }

    private String makeNumbersToString(List<String> numbers) {
        return String.join(CONCAT, numbers);
    }

    private void validateNumberOutOfRange(List<String> numbers) {
        numbers.forEach(number ->
                InputValidateUtils.inputOutOfRange(number, WinningNumberExceptionMessage.RANGE_ERROR.getMassage()));
    }

    private void validateNumberSize(List<String> numbers) {
        if (numbers.size() != WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException(WinningNumberExceptionMessage.SIZE_ERROR.getMassage());
        }
    }

    private void validateNumberReduplication(List<String> numbers) {
        long count = numbers.stream().distinct().count();

        if (count != numbers.size()) {
            throw new IllegalArgumentException(WinningNumberExceptionMessage.REDUPLICATION_ERROR.getMassage());
        }
    }

    private Set<Integer> makeWinningNumbers(List<String> numbers) {
        return numbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    public void validateReduplicationWithBonusBall(String number) {
        if (winningNumbers.contains(ConverterUtils.convertStringToInt(number))) {
            throw new IllegalArgumentException(WinningNumberExceptionMessage.REDUPLICATION_BONUS_BALL_ERROR.getMassage());
        }
    }

    public Set<Integer> getWinningNumbers() {
        return Collections.unmodifiableSet(winningNumbers);
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

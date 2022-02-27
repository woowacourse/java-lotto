package model.winningnumber;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import model.exception.WinningNumberException;
import utils.InputValidateUtils;

public class LottoWinningNumber {
    private static final int WINNING_NUMBER_SIZE = 6;
    private static final String CONCAT = "";

    private final Set<Integer> winningNumbers;

    public LottoWinningNumber(List<String> numbers) {
        inputBlank(numbers);
        InputValidateUtils.inputNumber(makeNumbersToString(numbers), WinningNumberException.NUMBER_ERROR.getMassage());
        validateNumberOutOfRange(numbers);
        validateNumberSize(numbers);
        validateNumberReduplication(numbers);
        this.winningNumbers = makeWinningNumbers(numbers);
    }

    private void inputBlank(List<String> numbers) {
        if (numbers == null || numbers.stream().anyMatch(String::isBlank)) {
            throw new IllegalArgumentException(WinningNumberException.BLANK_ERROR.getMassage());
        }
    }

    private String makeNumbersToString(List<String> numbers) {
        return String.join(CONCAT, numbers);
    }

    private void validateNumberOutOfRange(List<String> numbers) {
                numbers.forEach(number ->
                        InputValidateUtils.inputOutOfRange(number, WinningNumberException.RANGE_ERROR.getMassage()));
    }

    private void validateNumberSize(List<String> numbers) {
        if (numbers.size() != WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException(WinningNumberException.SIZE_ERROR.getMassage());
        }
    }

    private void validateNumberReduplication(List<String> numbers) {
        long count = numbers.stream().distinct().count();

        if (count != numbers.size()) {
            throw new IllegalArgumentException(WinningNumberException.REDUPLICATION_ERROR.getMassage());
        }
    }

    private Set<Integer> makeWinningNumbers(List<String> numbers) {
        return numbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    public void validateReduplicationWithBonusBall(String number) {
        if (winningNumbers.contains(Integer.parseInt(number))) {
            throw new IllegalArgumentException(WinningNumberException.REDUPLICATION_BONUS_BALL_ERROR.getMassage());
        }
    }

    public Set<Integer> getWinningNumbers() {
        return Collections.unmodifiableSet(winningNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoWinningNumber that = (LottoWinningNumber) o;
        return Objects.equals(winningNumbers, that.winningNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumbers);
    }
}

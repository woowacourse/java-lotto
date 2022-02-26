package model.winningnumber;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import utils.InputValidateUtils;

public class LottoWinningNumber {
    private static final String WINNING_NUMBER_ERROR_MESSAGE = "[Error]: 당첨 번호는 숫자여야 합니다.";
    private static final String WINNING_NUMBER_BLANK_ERROR_MESSAGE = "[Error]: 당첨 번호를 입력하세요.";
    private static final String WINNING_NUMBER_RANGE_ERROR_MESSAGE = "[Error]: 당첨 번호는 1~45 숫자여야 합니다.";
    private static final String WINNING_NUMBER_SIZE_ERROR_MESSAGE = "[Error]: 당첨 번호는 6개의 숫자여야 합니다.";
    private static final String WINNING_NUMBER_REDUPLICATION_ERROR_MESSAGE = "[Error]: 당첨 번호는 중복이 있으면 안됩니다";
    private static final String REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE = "[Error]: 당첨 번호와 보너스 볼이 중복됩니다.";
    private static final int WINNING_NUMBER_SIZE = 6;

    private final Set<Integer> winningNumbers;

    public LottoWinningNumber(List<String> numbers) {
        inputBlank(numbers);
        InputValidateUtils.inputNumber(makeNumbersToString(numbers), WINNING_NUMBER_ERROR_MESSAGE);
        validateNumberOutOfRange(numbers);
        validateNumberSize(numbers);
        validateNumberReduplication(numbers);
        this.winningNumbers = makeWinningNumbers(numbers);
    }

    private void inputBlank(List<String> numbers) {
        if (numbers == null || numbers.stream().anyMatch(String::isBlank)) {
            throw new IllegalArgumentException(WINNING_NUMBER_BLANK_ERROR_MESSAGE);
        }
    }

    private String makeNumbersToString(List<String> numbers) {
        return String.join("", numbers);
    }

    private void validateNumberOutOfRange(List<String> numbers) {
                numbers.forEach(number ->
                        InputValidateUtils.inputOutOfRange(number, WINNING_NUMBER_RANGE_ERROR_MESSAGE));
    }

    private void validateNumberSize(List<String> numbers) {
        if (numbers.size() != WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException(WINNING_NUMBER_SIZE_ERROR_MESSAGE);
        }
    }

    private void validateNumberReduplication(List<String> numbers) {
        long count = numbers.stream().distinct().count();

        if (count != numbers.size()) {
            throw new IllegalArgumentException(WINNING_NUMBER_REDUPLICATION_ERROR_MESSAGE);
        }
    }

    private Set<Integer> makeWinningNumbers(List<String> numbers) {
        return numbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    public void validateReduplicationWithBonusBall(String number) {
        if (winningNumbers.contains(Integer.parseInt(number))) {
            throw new IllegalArgumentException(REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE);
        }
    }

    public LottoWinningNumberDTO getWinningNumbersDTO() {
        return new LottoWinningNumberDTO(this.winningNumbers);
    }

    public Set<Integer> getWinningNumbers() {
        return winningNumbers;
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

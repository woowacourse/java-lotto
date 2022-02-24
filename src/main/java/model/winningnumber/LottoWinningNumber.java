package model.winningnumber;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

    private final List<Integer> winningNumbers;

    public LottoWinningNumber(String numbers) {
        InputValidateUtils.inputBlank(numbers, WINNING_NUMBER_BLANK_ERROR_MESSAGE);
        InputValidateUtils.inputNumber(makeNumbersToString(numbers), WINNING_NUMBER_ERROR_MESSAGE);
        validateNumberOutOfRange(numbers);
        validateNumberSize(numbers);
        validateNumberReduplication(numbers);
        this.winningNumbers = makeWinningNumbers(split(numbers));
    }

    private List<String> split(String numbers) {
        return Arrays.asList(numbers.replace(" ", "").split(","));
    }

    private String makeNumbersToString(String numbers) {
        return String.join("", split(numbers));
    }

    private void validateNumberOutOfRange(String numbers) {
        split(numbers)
                .forEach(number -> InputValidateUtils.inputOutOfRange(number, WINNING_NUMBER_RANGE_ERROR_MESSAGE));
    }

    private void validateNumberSize(String numbers) {
        if (split(numbers).size() != WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException(WINNING_NUMBER_SIZE_ERROR_MESSAGE);
        }
    }

    private void validateNumberReduplication(String numbers) {
        long count = split(numbers).stream().distinct().count();

        if (count != split(numbers).size()) {
            throw new IllegalArgumentException(WINNING_NUMBER_REDUPLICATION_ERROR_MESSAGE);
        }
    }

    private List<Integer> makeWinningNumbers(List<String> numbers) {
        return numbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public void validateReduplicationWithBonusBall(String number) {
        if (winningNumbers.contains(Integer.parseInt(number))) {
            throw new IllegalArgumentException(REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE);
        }
    }

    public LottoWinningNumberDTO getWinningNumbersDTO() {
        return new LottoWinningNumberDTO(this.winningNumbers);
    }

    public List<Integer> getWinningNumbers() {
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

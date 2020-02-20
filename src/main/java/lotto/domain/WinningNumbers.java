package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningNumbers {
    private static final String WINNING_NUMBERS_DELIMITER = ",";
    private static final int NUMBER_COUNT_PER_LOTTO = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> winningNumbers;

    public WinningNumbers(String winningNumbersInput) {
        this.winningNumbers = getValidatedWinningNumbers(winningNumbersInput);
    }

    private static void checkNoInput(String winningNumbersInput) {
        if (winningNumbersInput == null || winningNumbersInput.trim().isEmpty()) {
            throw new RuntimeException("당첨 번호를 입력하지 않으셨습니다.");
        }
    }

    private static void checkDelimiter(String winningNumbersInput) {
        if (!winningNumbersInput.contains(WINNING_NUMBERS_DELIMITER)) {
            throw new RuntimeException(String.format("당첨 번호는 %s로 나누어 입력해 주세요.", WINNING_NUMBERS_DELIMITER));
        }
    }

    private List<Integer> getValidatedWinningNumbers(String winningNumbersInput) {
        checkNoInput(winningNumbersInput);
        checkDelimiter(winningNumbersInput);
        List<String> splittedNumbers = split(winningNumbersInput);
        checkCount(splittedNumbers);
        checkType(splittedNumbers);
        List<Integer> winningNumbers = parseToNumbers(splittedNumbers);
        checkRange(winningNumbers);
        checkDuplicatedNumber(winningNumbers);
        return winningNumbers;
    }

    private List<String> split(String winningNumbersInput) {
        List<String> splittedNumbers = new ArrayList<>();
        String[] winningNumbers = winningNumbersInput.split(WINNING_NUMBERS_DELIMITER);
        for (int i = 0; i < winningNumbers.length; i++) {
            splittedNumbers.add(winningNumbers[i].trim());
        }
        return splittedNumbers;
    }

    private void checkCount(List<String> splittedNumbers) {
        if (splittedNumbers.size() != NUMBER_COUNT_PER_LOTTO) {
            throw new RuntimeException(String.format("당첨 번호는 %d개만 가능합니다.", NUMBER_COUNT_PER_LOTTO));
        }
    }

    private void checkType(List<String> splittedNumbers) {
        try {
            splittedNumbers.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new RuntimeException("숫자만 입력하시기 바랍니다.");
        }
    }

    private List<Integer> parseToNumbers(List<String> splittedNumbers) {
        return splittedNumbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void checkRange(List<Integer> winningNumbers) {
        int validNumberCount = winningNumbers.stream()
                .filter(integer -> MIN_NUMBER <= integer && integer <= MAX_NUMBER)
                .collect(Collectors.toList())
                .size();
        if (validNumberCount != NUMBER_COUNT_PER_LOTTO) {
            throw new RuntimeException(String.format("%d 이상 %d 이하의 숫자만 입력 가능합니다.", MIN_NUMBER, MAX_NUMBER));
        }
    }

    private void checkDuplicatedNumber(List<Integer> winningNumbers) {
        Set<Integer> numbers = new HashSet<>(winningNumbers);
        if (numbers.size() != winningNumbers.size()) {
            throw new RuntimeException("중복된 숫자가 입력되었습니다.");
        }
    }

    public List<Integer> getWinningNumbers() {
        return this.winningNumbers;
    }
}

package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningBalls {
    private static final String WINNING_NUMBERS_DELIMITER = ",";
    private static final int NUMBER_COUNT_PER_LOTTO = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Ball> winningBalls;

    public WinningBalls(String winningBalls) {
        validate(winningBalls);
        this.winningBalls = parseToBalls(split(winningBalls));
    }

    private void validate(String winningBallsInput) {
        checkNoInput(winningBallsInput);
        checkDelimiter(winningBallsInput);
        List<String> splittedNumbers = split(winningBallsInput);
        checkCount(splittedNumbers);
        checkType(splittedNumbers);
        List<Ball> winningBalls = parseToBalls(splittedNumbers);
        checkDuplicatedNumber(winningBalls);
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

    private List<String> split(String winningNumbersInput) {
        List<String> splittedNumbers = new ArrayList<>();
        String[] winningNumbers = winningNumbersInput.split(WINNING_NUMBERS_DELIMITER);
        for (String winningNumber : winningNumbers) {
            splittedNumbers.add(winningNumber.trim());
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

    private List<Ball> parseToBalls(List<String> splittedNumbers) {
        return splittedNumbers.stream()
                .map(Integer::parseInt)
                .map(Ball::valueOf)
                .collect(Collectors.toList());
    }

    private void checkDuplicatedNumber(List<Ball> winningBalls) {
        Set<Ball> numbers = new HashSet<>(winningBalls);
        if (numbers.size() != winningBalls.size()) {
            throw new RuntimeException("중복된 숫자가 입력되었습니다.");
        }
    }

    public List<Ball> getWinningBalls() {
        return this.winningBalls;
    }
}

package lotto.domain;

import lotto.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WinningBalls {
    private static final String WINNING_NUMBERS_DELIMITER = ",";
    private static final int NUMBER_COUNT_PER_LOTTO = 6;

    private final Lotto winningBalls;

    public WinningBalls(String winningBalls) {
        validate(winningBalls);
        this.winningBalls = new Lotto(split(winningBalls).stream()
                .map(Ball::valueOf)
                .collect(Collectors.toList()));
    }

    private static void checkEmpty(String winningNumbersInput) {
        if (winningNumbersInput == null || winningNumbersInput.trim().isEmpty()) {
            throw new InvalidInputException("당첨 번호를 입력하지 않으셨습니다.");
        }
    }

    private static void checkDelimiter(String winningNumbersInput) {
        if (!winningNumbersInput.contains(WINNING_NUMBERS_DELIMITER)) {
            throw new InvalidInputException(String.format("당첨 번호는 %s로 나누어 입력해 주세요.", WINNING_NUMBERS_DELIMITER));
        }
    }

    private void validate(String winningBallsInput) {
        checkEmpty(winningBallsInput);
        checkDelimiter(winningBallsInput);
        List<String> splittedNumbers = split(winningBallsInput);
        checkCount(splittedNumbers);
        checkType(splittedNumbers);
        Lotto winningBalls = new Lotto(splittedNumbers.stream()
                .map(Ball::valueOf)
                .collect(Collectors.toList()));
        checkDuplicatedNumber(winningBalls);
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
            throw new InvalidInputException(String.format("당첨 번호는 %d개만 가능합니다.", NUMBER_COUNT_PER_LOTTO));
        }
    }

    private void checkType(List<String> splittedNumbers) {
        try {
            splittedNumbers.forEach(Integer::parseInt);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("숫자만 입력하시기 바랍니다.");
        }
    }

    private void checkDuplicatedNumber(Lotto winningBalls) {
        if (winningBalls.isDuplicated()) {
            throw new InvalidInputException("중복된 숫자가 입력되었습니다.");
        }
    }

    public boolean contains(String bonusBall) {
        return winningBalls.contains(bonusBall);
    }

    public Lotto getWinningBalls() {
        return this.winningBalls;
    }
}

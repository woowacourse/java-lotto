package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WinningBalls {
    private static final String WINNING_NUMBERS_DELIMITER = ",";
    private static final int NUMBER_COUNT_PER_LOTTO = 6;

    private final Lotto winningBalls;

    public WinningBalls(String winningBalls) {
        validate(winningBalls);
        this.winningBalls = convertToLotto(split(winningBalls));
    }

    private void validate(String winningBallsInput) {
        checkNoInput(winningBallsInput);
        checkDelimiter(winningBallsInput);
        List<String> splittedNumbers = split(winningBallsInput);
        checkCount(splittedNumbers);
        checkType(splittedNumbers);
        Lotto winningBalls = convertToLotto(splittedNumbers);
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
            splittedNumbers.forEach(Integer::parseInt);
        } catch (NumberFormatException e) {
            throw new RuntimeException("숫자만 입력하시기 바랍니다.");
        }
    }

    private Lotto convertToLotto(List<String> splittedNumbers) {
        return new Lotto(splittedNumbers.stream()
                .map(Integer::parseInt)
                .map(Ball::valueOf)
                .collect(Collectors.toList()));
    }

    private void checkDuplicatedNumber(Lotto winningBalls) {
        if (winningBalls.isDuplicated()) {
            throw new RuntimeException("중복된 숫자가 입력되었습니다.");
        }
    }

    public boolean contains(String bonusBall) {
        return winningBalls.contains(bonusBall);
    }

    public Lotto getWinningBalls() {
        return this.winningBalls;
    }
}

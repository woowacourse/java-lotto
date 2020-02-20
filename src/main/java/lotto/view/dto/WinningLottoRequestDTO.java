package lotto.view.dto;

import lotto.exception.ConvertFailException;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningLottoRequestDTO {
    private static final String MESSAGE_INVALID_BALL_SIZE = "볼 %d개, 입력한 볼의 갯수가 6개가 아닙니다.";
    private static final String PARSE_FAIL_EXCEPTION_MESSAGE = "%s : 숫자가 아닌 문자가 존재합니다.";
    private static final String COMMA = ",";
    private static final int LOTTO_BALL_SIZE = 6;

    private final Set<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLottoRequestDTO(String winningNumbers, int bonusNumber) {
        Set<Integer> numbers = collectNumber(winningNumbers);
        validateSize(numbers);
        this.bonusNumber = bonusNumber;
        this.winningNumbers = numbers;
    }

    private Set<Integer> collectNumber(String winningNumbers) {
        try {
            return getSet(winningNumbers);
        } catch (NumberFormatException e) {
            throw new ConvertFailException(String.format(PARSE_FAIL_EXCEPTION_MESSAGE, winningNumbers));
        }
    }

    private Set<Integer> getSet(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(COMMA))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    private void validateSize(Set<Integer> winningNumbers) {
        if (winningNumbers.size() != LOTTO_BALL_SIZE) {
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_BALL_SIZE, winningNumbers.size()));
        }
    }

    public Set<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}

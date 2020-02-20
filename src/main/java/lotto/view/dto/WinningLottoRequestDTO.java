package lotto.view.dto;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

// TODO: 테스트 코드 짜기
public class WinningLottoRequestDTO {
    private static final int LOTTO_BALL_SIZE = 6;
    private static final String MESSAGE_INVALID_BALL_SIZE = "볼 %d개: %s, 입력한 볼의 갯수가 6개가 아닙니다.";
    private final Set<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLottoRequestDTO(String winningNumbers, int bonusNumber) {
        Set<Integer> numbers = collectNumber(winningNumbers);
        validateSize(numbers);
        this.bonusNumber = bonusNumber;
        this.winningNumbers = numbers;
    }

    private Set<Integer> collectNumber(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    private void validateSize(Set<Integer> winningNumbers) {
        if (winningNumbers.size() != LOTTO_BALL_SIZE) {
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_BALL_SIZE, winningNumbers.size(), winningNumbers.toString()));
        }
    }

    public Set<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}

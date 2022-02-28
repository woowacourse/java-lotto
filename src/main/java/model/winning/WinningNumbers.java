package model.winning;

import java.util.List;
import java.util.stream.Collectors;
import model.lottotickets.vo.Number;

public class WinningNumbers {
    private static final int TICKET_SIZE = 6;
    private static final String NOT_CORRECT_TICKET_SIZE_ERROR_MESSAGE = "입력한 당첨 번호가 6개가 아닙니다.";

    private final List<Number> winningNumbers;
    private final Number bonusNumber;

    public WinningNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        checkSize(winningNumbers);
        this.winningNumbers = winningNumbers.stream()
                .map(Number::new)
                .collect(Collectors.toList());
        this.bonusNumber = new Number(bonusNumber);
    }

    private void checkSize(final List<Integer> winningNumbers) {
        if (winningNumbers.size() != TICKET_SIZE) {
            throw new IllegalArgumentException(NOT_CORRECT_TICKET_SIZE_ERROR_MESSAGE);
        }
    }

    public Number getBonusNumber() {
        return bonusNumber;
    }

    public List<Number> getWinningNumbers() {
        return winningNumbers;
    }
}

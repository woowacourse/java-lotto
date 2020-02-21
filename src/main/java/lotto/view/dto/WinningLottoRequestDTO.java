package lotto.view.dto;

import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import lotto.exception.ConvertFailException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningLottoRequestDTO {
    private static final int LOTTO_BALL_SIZE = 6;
    private static final String MESSAGE_INVALID_BALL_SIZE = "볼 %d개, 입력한 볼의 갯수가 6개가 아닙니다.";
    private static final String COMMA = ",";
    private static final String PARSE_FAIL_EXCEPTION_MESSAGE = "%s : 숫자가 아닌 문자가 존재합니다.";
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
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    private void validateSize(Set<Integer> winningNumbers) {
        if (winningNumbers.size() != LOTTO_BALL_SIZE) {
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_BALL_SIZE, winningNumbers.size()));
        }
    }

    public WinningLotto toWinningLotto() {
        Set<LottoBall> winningLotto = new HashSet<>();
        for (int number : this.winningNumbers) {
            winningLotto.add(LottoBallFactory.findLottoBallByNumber(number));
        }
        return new WinningLotto(winningLotto, LottoBallFactory.findLottoBallByNumber(this.bonusNumber));
    }

    public Set<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}

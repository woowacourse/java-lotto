package lotto.domain;

import static java.util.stream.Collectors.*;
import static lotto.domain.BallType.BONUS;

import java.util.List;
import java.util.Set;

public class WinningNumbers {

    private static final int DEFAULT_WINNING_NUMBERS_SIZE = 7;
    private static final String DUPLICATED_WINNING_NUMBERS_ERROR_MESSAGE =
            "당첨 번호는 보너스 볼을 포함하여 총 7자리 입니다. 또한 중복될 수 없습니다.";

    private final List<WinningNumber> winningNumbers;

    public WinningNumbers(List<Integer> normalWinningNumbers, Integer bonusWinningNumber) {
        List<WinningNumber> winningNumbers = normalWinningNumbers.stream()
                .map(WinningNumber::new)
                .collect(toList());

        winningNumbers.add(new WinningNumber(bonusWinningNumber, BONUS));

        validateSize(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private void validateSize(List<WinningNumber> winningNumbers) {
        Set<Integer> noneDuplicatedWinningNumbers = winningNumbers.stream()
                .map(WinningNumber::getWinningNumber)
                .collect(toSet());

        if (noneDuplicatedWinningNumbers.size() != DEFAULT_WINNING_NUMBERS_SIZE) {
            throw new IllegalArgumentException(DUPLICATED_WINNING_NUMBERS_ERROR_MESSAGE);
        }
    }

    public Rank compare(LottoTicket lottoTicket) {
        return Rank.of(getCorrectCount(lottoTicket), isBonus(lottoTicket));
    }

    private int getCorrectCount(LottoTicket lottoTicket) {
        return winningNumbers.stream()
                .filter(lottoTicket::isSame)
                .collect(toList())
                .size();
    }

    private boolean isBonus(LottoTicket lottoTicket) {
        return winningNumbers.stream()
                .filter(lottoTicket::isSame)
                .anyMatch(WinningNumber::isBonus);
    }
}

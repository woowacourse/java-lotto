package lotto.domain;

import static java.util.stream.Collectors.*;
import static lotto.domain.BallType.BONUS;
import static lotto.domain.BallType.NORMAL;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbers {

    private final List<WinningNumber> winningNumbers;

    public WinningNumbers(List<Integer> normalWinningNumbers, Integer bonusWinningNumber) {
        List<WinningNumber> winningNumbers = new ArrayList<>();

        for (Integer normalWinningNumber : normalWinningNumbers) {
            WinningNumber winningNumber = new WinningNumber(normalWinningNumber, NORMAL);
            winningNumbers.add(winningNumber);
        }

        winningNumbers.add(new WinningNumber(bonusWinningNumber, BONUS));

        this.winningNumbers = winningNumbers;
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

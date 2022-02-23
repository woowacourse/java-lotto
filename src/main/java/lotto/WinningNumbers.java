package lotto;

import java.util.List;

public class WinningNumbers {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Rank isWinning(LottoTicket lottoTicket) {
        return lottoTicket.compareNumbers(winningNumbers, bonusNumber);
    }
}

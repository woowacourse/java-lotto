package lotto;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbers {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Rank> isWinning(List<LottoTicket> lottoTickets) {
        List<Rank> ranks = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            ranks.add(lottoTicket.compareNumbers(winningNumbers, bonusNumber));
        }
        return ranks;
    }
}

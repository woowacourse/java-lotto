package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ticket {

    private final List<LottoNumber> ticket;

    public Ticket(List<LottoNumber> ticket) {
        this.ticket = ticket;
    }

    private int getWinningNumbersMatchCount(WinningNumbers winningNumbers) {
        return winningNumbers.getMatchCount(ticket);
    }

    private boolean isBonusNumberMatch(BonusNumber bonusNumber) {
        return bonusNumber.isMatch(ticket);
    }

    public Rank getRank(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        int winningNumberMatchCount = getWinningNumbersMatchCount(winningNumbers);
        boolean bonusNumberMatch = isBonusNumberMatch(bonusNumber);
        return Rank.getRank(winningNumberMatchCount, bonusNumberMatch);
    }
}

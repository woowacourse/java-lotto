package domain;

import domain.ticket.LottoTicket;
import domain.ticket.Ticket;
import domain.ticket.WinningTicket;

import java.util.List;

public class WinningNumbers {
    private final Ticket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningTicket = new WinningTicket(winningNumbers);
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);
    }

    private void validate(final List<Integer> winningNumbers, final int bonusNumber) {
        validateDistinctBonus(winningNumbers, bonusNumber);
    }

    private void validateDistinctBonus(final List<Integer> winningNumbers, final int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호에 보너스 번호가 포함되어 있습니다:" + bonusNumber);
        }
    }

    public int countMatching(final LottoTicket lottoTicket) {
        return winningTicket.countSameNumbers(lottoTicket);
    }

    public boolean hasBonus(final LottoTicket lottoTicket) {
        return lottoTicket.contains(bonusNumber);
    }
}

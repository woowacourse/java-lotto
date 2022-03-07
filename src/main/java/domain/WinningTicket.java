package domain;

import java.util.Set;

public class WinningTicket {
    private final LottoTicket ticket;
    private final LottoNumber bonusNumber;

    private WinningTicket(LottoTicket ticket, LottoNumber bonusNumber) {
        this.ticket = ticket;
        this.bonusNumber = bonusNumber;
    }

    public static WinningTicket create(Set<Integer> winningNumberValues, int bonusNumber) {
        return new WinningTicket(LottoTicket.fromNumberValues(winningNumberValues), LottoNumber.valueOf(bonusNumber));
    }

    public int compareMatchCount(LottoTicket lottoTicket) {
        Set<Integer> lottoNumberValues = lottoTicket.getLottoNumberValues();
        Set<Integer> winningNumberValues = ticket.getLottoNumberValues();
        winningNumberValues.retainAll(lottoNumberValues);
        return winningNumberValues.size();
    }

    public boolean isMatchBonusNumber(LottoTicket lottoTicket) {
        Set<Integer> lottoNumbers = lottoTicket.getLottoNumberValues();
        return lottoNumbers.contains(bonusNumber.getValue());
    }
}

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
        validateSize(winningNumberValues);
        return new WinningTicket(LottoTicket.fromNumberValues(winningNumberValues), new LottoNumber(bonusNumber));
    }

    private static void validateSize(Set<Integer> winningNumbers) {
        if (winningNumbers.size() != LottoTicket.SIZE) {
            throw new IllegalArgumentException(LottoTicket.SIZE_ERROR_MESSAGE);
        }
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

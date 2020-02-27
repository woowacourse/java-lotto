package lotto.domain;

import lotto.exception.WinningNumbersException;

import java.util.List;

public class WinningNumbers {
    private final LottoTicket winningLottoTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
        validateDuplication(winningLottoTicket, bonusNumber);
        this.winningLottoTicket = winningLottoTicket;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplication(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
        if (winningLottoTicket.contains(bonusNumber)) {
            throw new WinningNumbersException();
        }
    }

    public List<Rank> checkOutLottos(LottoTickets lottoTickets) {
        return lottoTickets.checkOutLottos(winningLottoTicket, bonusNumber);
    }
}
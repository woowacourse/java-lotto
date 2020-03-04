package lotto.domain;

import lotto.exception.WinningNumbersException;

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

    public Ranks checkOutLottos(LottoTickets lottoTickets) {
        return lottoTickets.checkOutLottos(winningLottoTicket, bonusNumber);
    }
}
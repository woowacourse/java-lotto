package lottogame.domain.machine;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;
import lottogame.domain.ticket.LottoTicket;

public class LottoWinningMachine {

    private final LottoNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public LottoWinningMachine(final LottoNumbers winningNumbers, final LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int countMatchedWinningNumber(final LottoTicket lottoTicket) {
        return (int) lottoTicket.getLottoNumbers()
            .stream()
            .filter(winningNumbers::contains)
            .count();
    }

    public boolean isMatchBonusNumber(final LottoTicket lottoTicket){
        return lottoTicket.getLottoNumbers().contains(bonusNumber);
    }
}

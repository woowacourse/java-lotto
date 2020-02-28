package lotto.domain.lotto;

import lotto.domain.result.Rank;

public class WinningLotto {

    private LottoTicket winningNumbers;
    private LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningNumbers, LottoNumber bonusNumber) {
        validateLottoTicketHasBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateLottoTicketHasBonusNumber(LottoTicket winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new WinningLottoException();
        }
    }

    public Rank getRank(LottoTicket lottoTicket) {
        int countOfMatches = winningNumbers.countMatches(lottoTicket);
        boolean bonusMatches = lottoTicket.contains(bonusNumber);

        return Rank.valueOf(countOfMatches, bonusMatches);
    }
}

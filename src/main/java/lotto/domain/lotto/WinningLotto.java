package lotto.domain.lotto;

import java.util.Set;

import lotto.domain.result.Rank;

public class WinningLotto {

    private static final String DUPLICATE_LOTTO_NUMBERS_BONUS_NUMBER = "보너스 번호와 당첨 번호는 중복될 수 없습니다.";

    private LottoTicket winningLottoTicket;
    private LottoNumber bonusLottoNumber;

    public WinningLotto(Set<Integer> winningLottoTicket, int bonusLottoNumber) {
        validateLottoTicketHasBonusNumber(winningLottoTicket, bonusLottoNumber);
        this.winningLottoTicket = new LottoTicket(winningLottoTicket);
        this.bonusLottoNumber = LottoNumber.publishLottoNumber(bonusLottoNumber);
    }

    private void validateLottoTicketHasBonusNumber(Set<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBERS_BONUS_NUMBER);
        }
    }

    public Rank checkOutRank(LottoTicket lottoTicket) {
        int countOfMatches = winningLottoTicket.countMatches(lottoTicket);
        boolean bonusMatches = lottoTicket.contains(bonusLottoNumber);
        return Rank.valueOf(countOfMatches, bonusMatches);
    }
}

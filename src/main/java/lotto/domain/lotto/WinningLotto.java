package lotto.domain.lotto;

import lotto.domain.result.Rank;

public class WinningLotto {

    private static final String DUPLICATE_LOTTO_NUMBERS_BONUS_NUMBER = "보너스 번호와 당첨 번호는 중복될 수 없습니다.";
    private LottoTicket winningNumbers;
    private LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningNumbers, LottoNumber bonusNumber) {
        validateLottoTicketHasBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateLottoTicketHasBonusNumber(LottoTicket winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBERS_BONUS_NUMBER);
        }
    }

    public Rank getRank(LottoTicket lottoTicket) {
        int countOfMatches = winningNumbers.countMatches(lottoTicket);
        boolean bonusMatches = lottoTicket.contains(bonusNumber);

        return Rank.valueOf(countOfMatches, bonusMatches);
    }
}

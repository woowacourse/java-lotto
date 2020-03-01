package lotto.domain.lotto;

import lotto.domain.result.Rank;

public class WinningLotto {

    private LottoTicket winningNumbers;
    private LottoNumber bonusNumber;

    private WinningLotto(LottoTicket winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto from(LottoTicket winningNumbers, LottoNumber bonusNumber) {
        validateBonusNumber(winningNumbers, bonusNumber);
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private static void validateBonusNumber(LottoTicket winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호와 당첨 번호는 중복될 수 없습니다.");
        }
    }

    public Rank getRank(LottoTicket lottoTicket) {
        int countOfMatches = winningNumbers.countMatches(lottoTicket);
        boolean bonusMatches = lottoTicket.contains(bonusNumber);

        return Rank.valueOf(countOfMatches, bonusMatches);
    }
}

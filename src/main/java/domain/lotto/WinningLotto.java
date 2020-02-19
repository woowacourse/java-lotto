package domain.lotto;

import domain.result.Rank;

public class WinningLotto {

    private LottoTicket winningLotto;
    private LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.has(bonusNumber)) {
            throw new IllegalArgumentException();
        }
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank getRank(LottoTicket lottoTicket) {
        int countOfMatches = winningLotto.countMatches(lottoTicket);
        boolean bonusMatches = lottoTicket.contains(bonusNumber);

        return Rank.valueOf(countOfMatches, bonusMatches);
    }
}

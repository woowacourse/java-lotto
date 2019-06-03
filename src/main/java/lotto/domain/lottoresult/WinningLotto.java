package lotto.domain.lottoresult;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;

public class WinningLotto {
    private final LottoTicket winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningLotto, LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
        validateBonusNumber();
    }

    private void validateBonusNumber() {
        if (winningLotto.contains(bonusNumber)) {
            throw new InvalidWinningLottoException("당첨 번호에 포함된 번호입니다.");
        }
    }

    public LottoRank checkLottoRank(LottoTicket userLottoTicket) {
        return LottoRank
                .rankOf(winningLotto.countOfMatch(userLottoTicket),
                        userLottoTicket.contains(bonusNumber));
    }
}

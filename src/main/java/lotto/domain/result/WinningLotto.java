package lotto.domain.result;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;

public class WinningLotto {
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningTicket, LottoNumber bonusNumber) {
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
        validateWinningLotto();
    }

    private void validateWinningLotto() {
        if (winningTicket.isContainBonusNumber(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호가 중복됩니다.");
        }
    }

    public LottoTicket getWinningTicket() {
        return winningTicket;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}

package lotto.domain;

import lotto.exception.IllegalWinningLottoException;

public class WinningLotto {
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningTicket, LottoNumber bonusNumber) {
        validateWinningLotto(winningTicket, bonusNumber);
        this.winningTicket = new LottoTicket(winningTicket.getLottoTicket());
        this.bonusNumber = new LottoNumber(bonusNumber.toString());
    }

    private void validateWinningLotto(LottoTicket winningTicket, LottoNumber bonusNumber) {
        if (winningTicket.hasLottoNumberInTicket(bonusNumber)) {
            throw new IllegalWinningLottoException(bonusNumber + " : 티켓 내 숫자와 보너스 번호가 중복됩니다.");
        }
    }

    public LottoTicket getWinningTicket() {
        return winningTicket;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
